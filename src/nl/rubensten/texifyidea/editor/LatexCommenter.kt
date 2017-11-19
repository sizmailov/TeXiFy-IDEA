package nl.rubensten.texifyidea.editor

import com.intellij.lang.CodeDocumentationAwareCommenter
import com.intellij.psi.PsiComment
import com.intellij.psi.tree.IElementType
import nl.rubensten.texifyidea.psi.LatexTypes

/**
 * @author Sten Wessel
 */
open class LatexCommenter : CodeDocumentationAwareCommenter {

    override fun getLineCommentPrefix() = "%"

    override fun getBlockCommentPrefix() = ""

    override fun getBlockCommentSuffix() = ""

    override fun getCommentedBlockCommentPrefix() = ""

    override fun getCommentedBlockCommentSuffix() = ""

    override fun isDocumentationComment(element: PsiComment?) = false

    override fun getDocumentationCommentTokenType(): IElementType? = null

    override fun getLineCommentTokenType() = LatexTypes.COMMENT_TOKEN!!

    override fun getBlockCommentTokenType() = null

    override fun getDocumentationCommentLinePrefix() = null

    override fun getDocumentationCommentPrefix() = null

    override fun getDocumentationCommentSuffix() = null
}
