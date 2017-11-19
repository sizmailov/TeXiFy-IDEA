package nl.rubensten.texifyidea.formatting

import com.intellij.formatting.Wrap
import com.intellij.formatting.WrapType
import com.intellij.lang.ASTNode
import com.intellij.psi.codeStyle.CodeStyleSettings
import nl.rubensten.texifyidea.LatexLanguage

/**
 *
 * @author Sten Wessel
 */
class LatexWrappingStrategy(settings: CodeStyleSettings) {

    private val commonSettings = settings.getCommonSettings(LatexLanguage.INSTANCE)

    fun getWrap(element: ASTNode): Wrap? {
        return Wrap.createWrap(WrapType.NONE, false)
    }
}
