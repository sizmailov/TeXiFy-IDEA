package nl.hannahsten.texifyidea.psi;

import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import nl.hannahsten.texifyidea.index.stub.LatexCommandsStub;
import nl.hannahsten.texifyidea.util.files.ReferencedFileSetService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is a mixin for LatexCommandsImpl. We use a separate mixin class instead of [LatexPsiImplUtil] because we need to add an instance variable
 * in order to implement [getName] and [setName] correctly.
 */
public class LatexCommandsImplMixin extends StubBasedPsiElementBase<LatexCommandsStub> implements PsiNameIdentifierOwner {


    public String name;

    public LatexCommandsImplMixin(@NotNull LatexCommandsStub stub, @NotNull IStubElementType nodeType) {
        super(stub, nodeType);
    }

    public LatexCommandsImplMixin(@NotNull ASTNode node) {
        super(node);
    }

    public LatexCommandsImplMixin(LatexCommandsStub stub, IElementType nodeType, ASTNode node) {
        super(stub, nodeType, node);
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        this.name = name;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "LatexCommandsImpl(COMMANDS)[STUB]{" + getName() + "}";
    }

    @NotNull
    @Override
    public TextRange getTextRangeInParent() {
        return null;
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof LatexVisitor) {
            accept((LatexVisitor) visitor);
        }
        else {
            super.accept(visitor);
        }
    }

    @Override
    public void subtreeChanged() {
        ReferencedFileSetService setService = ReferencedFileSetService.getInstance(getProject());
        setService.dropCaches(getContainingFile());
        super.subtreeChanged();
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return this;
    }
}
