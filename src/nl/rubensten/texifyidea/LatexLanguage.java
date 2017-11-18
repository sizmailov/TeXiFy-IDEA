package nl.rubensten.texifyidea;

import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;

/**
 * @author Sten Wessel
 */
public class LatexLanguage extends Language {

    public static final LatexLanguage INSTANCE = new LatexLanguage();

    private LatexLanguage() {
        super("Latex");
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "LaTeX";
    }
}
