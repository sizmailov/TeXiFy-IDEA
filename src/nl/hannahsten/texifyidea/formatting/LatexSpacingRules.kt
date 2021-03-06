package nl.hannahsten.texifyidea.formatting

import com.intellij.formatting.Spacing
import com.intellij.psi.codeStyle.CodeStyleSettings
import nl.hannahsten.texifyidea.LatexLanguage
import nl.hannahsten.texifyidea.psi.LatexTypes.*
import nl.hannahsten.texifyidea.settings.codestyle.LatexCodeStyleSettings

/**
 *
 * @author Sten Wessel, Abby Berkers
 */
fun createSpacingBuilder(settings: CodeStyleSettings): TexSpacingBuilder {
    fun createSpacing(minSpaces: Int, maxSpaces: Int, minLineFeeds: Int, keepLineBreaks: Boolean, keepBlankLines: Int): Spacing =
            Spacing.createSpacing(minSpaces, maxSpaces, minLineFeeds, keepLineBreaks, keepBlankLines)

    val latexSettings = settings.getCustomSettings(LatexCodeStyleSettings::class.java)
    val latexCommonSettings = settings.getCommonSettings(LatexLanguage.INSTANCE)

    return rules(latexCommonSettings) {

        simple {
            between(NORMAL_TEXT_WORD, NORMAL_TEXT_WORD).spaces(1)
            before(ENVIRONMENT_CONTENT).lineBreakInCode()
        }

        custom {
            // Insert a new line between the end of environment content and the end command.
            inPosition(parent = ENVIRONMENT, left = ENVIRONMENT_CONTENT, right = END_COMMAND).spacing(
                    Spacing.createSpacing(0, Int.MAX_VALUE, 1, latexCommonSettings.KEEP_LINE_BREAKS, latexCommonSettings.KEEP_BLANK_LINES_IN_CODE)
            )
        }

        custom {
            fun commentSpacing(minSpaces: Int): Spacing {
                if (latexCommonSettings.KEEP_FIRST_COLUMN_COMMENT) {
                    return Spacing.createKeepingFirstColumnSpacing(minSpaces, Int.MAX_VALUE, latexCommonSettings.KEEP_LINE_BREAKS, latexCommonSettings.KEEP_BLANK_LINES_IN_CODE)
                }
                return createSpacing(minSpaces, Int.MAX_VALUE, 0, latexCommonSettings.KEEP_LINE_BREAKS, latexCommonSettings.KEEP_BLANK_LINES_IN_CODE)
            }

            inPosition(right = COMMENT_TOKEN).spacing(commentSpacing(0))

            // Make sure the number of new lines before a sectioning command is
            // as much as the user specified in the settings.
            // BUG? Does not work for a command that immediately follows
            // \begin{document}. But no one should start their document like
            // that anyway.
            customRule { _, _, right ->
                LatexCodeStyleSettings.blankLinesOptions.forEach {
                    if (right.node?.text?.matches(Regex("\\${it.value}\\{.*\\}")) == true) {
                        return@customRule createSpacing(
                                minSpaces = 0,
                                maxSpaces = Int.MAX_VALUE,
                                minLineFeeds = it.key.get(latexSettings) + 1,
                                keepLineBreaks = false,
                                keepBlankLines = 0)
                    }
                }
                return@customRule null
            }
        }
    }
}