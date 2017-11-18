package nl.rubensten.texifyidea.settings

import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CustomCodeStyleSettings
import nl.rubensten.texifyidea.LatexLanguage

/**
 *
 * @author Sten Wessel
 */
class LatexCodeStyleSettings(container: CodeStyleSettings) : CustomCodeStyleSettings(LatexLanguage.INSTANCE.id, container) {


}
