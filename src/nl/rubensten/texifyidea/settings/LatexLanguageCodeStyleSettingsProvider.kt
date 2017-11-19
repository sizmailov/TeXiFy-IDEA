package nl.rubensten.texifyidea.settings

import com.intellij.application.options.SmartIndentOptionsEditor
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable.WrappingOrBraceOption.*
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider.SettingsType.*
import nl.rubensten.texifyidea.LatexLanguage
import nl.rubensten.texifyidea.util.Magic
import nl.rubensten.texifyidea.util.removeHtmlTags

/**
 *
 * @author Sten Wessel
 */
class LatexLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {

    companion object {

        private val demoText = Magic.General.demoText.removeHtmlTags()
    }

    override fun getLanguage() = LatexLanguage.INSTANCE!!

    override fun getCodeSample(settingsType: SettingsType) = demoText

    override fun getIndentOptionsEditor() = SmartIndentOptionsEditor()

    override fun getDefaultCommonSettings() = CommonCodeStyleSettings(language).also { it.initIndentOptions() }

    override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
        when (settingsType) {

            SPACING_SETTINGS -> customizeSpacingSettings(consumer)
            WRAPPING_AND_BRACES_SETTINGS -> customizeWrappingAndBracesSettings(consumer)
            BLANK_LINES_SETTINGS -> customizeBlankLinesSettings(consumer)

            else -> return
        }
    }

    private fun customizeSpacingSettings(consumer: CodeStyleSettingsCustomizable) {
        // TODO: add remove multiple spaces option
    }

    private fun customizeWrappingAndBracesSettings(consumer: CodeStyleSettingsCustomizable) {
        consumer.showStandardOptions(*arrayOf(
                RIGHT_MARGIN,
                WRAP_ON_TYPING,
                WRAP_LONG_LINES,
                KEEP_FIRST_COLUMN_COMMENT
        ).map { it.name }.toTypedArray())
    }

    private fun customizeBlankLinesSettings(consumer: CodeStyleSettingsCustomizable) {
        // TODO: add custom settings
    }
}
