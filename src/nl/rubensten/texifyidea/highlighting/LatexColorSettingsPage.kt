package nl.rubensten.texifyidea.highlighting

import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import nl.rubensten.texifyidea.TexifyIcons
import nl.rubensten.texifyidea.util.Magic

/**
 * @author Ruben Schellekens, Sten Wessel
 */
class LatexColorSettingsPage : ColorSettingsPage {

    companion object {

        val DESCRIPTORS = arrayOf(
                AttributesDescriptor("Braces", LatexSyntaxHighlighter.BRACES),
                AttributesDescriptor("Brackets", LatexSyntaxHighlighter.BRACKETS),
                AttributesDescriptor("Optional parameters", LatexSyntaxHighlighter.OPTIONAL_PARAM),
                AttributesDescriptor("Commands", LatexSyntaxHighlighter.COMMAND),
                AttributesDescriptor("Commands in inline math mode", LatexSyntaxHighlighter.COMMAND_MATH_INLINE),
                AttributesDescriptor("Commands in display math mode", LatexSyntaxHighlighter.COMMAND_MATH_DISPLAY),
                AttributesDescriptor("Comments", LatexSyntaxHighlighter.COMMENT),
                AttributesDescriptor("Inline math", LatexSyntaxHighlighter.INLINE_MATH),
                AttributesDescriptor("Display math", LatexSyntaxHighlighter.DISPLAY_MATH),
                AttributesDescriptor("Stars", LatexSyntaxHighlighter.STAR)
        )

        val DEMO_TAGS = mapOf(
            "displayCommand" to LatexSyntaxHighlighter.COMMAND_MATH_DISPLAY,
                "inlineCommand" to LatexSyntaxHighlighter.COMMAND_MATH_INLINE,
                "displayMath" to LatexSyntaxHighlighter.DISPLAY_MATH,
                "inlineMath" to LatexSyntaxHighlighter.INLINE_MATH,
                "optionalParam" to LatexSyntaxHighlighter.OPTIONAL_PARAM,
                "comment" to LatexSyntaxHighlighter.COMMENT
        )
    }

    override fun getIcon() = TexifyIcons.LATEX_FILE!!

    override fun getHighlighter() = LatexSyntaxHighlighter()

    override fun getDemoText() = Magic.General.demoText

    override fun getAdditionalHighlightingTagToDescriptorMap() = DEMO_TAGS

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<out ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY!!

    override fun getDisplayName() = "LaTeX"
}
