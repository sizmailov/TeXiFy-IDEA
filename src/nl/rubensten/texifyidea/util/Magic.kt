package nl.rubensten.texifyidea.util

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.psi.PsiElement
import nl.rubensten.texifyidea.TexifyIcons
import nl.rubensten.texifyidea.inspections.latex.LatexLineBreakInspection
import nl.rubensten.texifyidea.lang.Package
import org.intellij.lang.annotations.Language
import java.util.regex.Pattern

typealias LatexPackage = Package
typealias RegexPattern = Pattern

/**
 * Magic constants are awesome!
 *
 * @author Ruben Schellekens
 */
object Magic {

    /**
     * @author Ruben Schellekens
     */
    object General {

        @JvmField val pathPackageRoot = "/nl/rubensten/texifyidea"
        @JvmField val emptyStringArray = arrayOfNulls<String>(0)
        @JvmField val emptyPsiElementArray = arrayOfNulls<PsiElement>(0)
        @JvmField val noQuickFix: LocalQuickFix? = null

        @Language("Latex")
        @JvmField val demoText = """
                |%
                |%  An amazing example for LaTeX.
                |%
                |\documentclass[<optionalParam>12pt,a4paper</optionalParam>]{article}
                |
                |% Package imports.
                |\usepackage{amsmath}
                |\usepackage{comment}
                |
                |% Start document.
                |\begin{document}
                |
                |    % Make title.
                |    \title{A Very Simple \LaTeXe{} Template}
                |    \author{
                |            Henk-Jan\\Department of YUROP\\University of Cheese\\
                |            Windmill City, 2198 AL, \underline{Tulipa}
                |    }
                |    \date{\today}
                |    \maketitle
                |
                |    % Start writing amazing stuff now.
                |    \begin{abstract}
                |        This is the paper's abstract \ldots.
                |    \end{abstract}
                |
                |    <comment>\begin{comment}
                |        Yes, even comment environments get highlighted.
                |    \end{comment}</comment>
                |
                |    \section{Introduction}\label{sec:introduction}
                |    This is time for all good men to come to the aid of their party!
                |
                |    \paragraph{Mathematics}
                |    Please take a look at the value of <inlineMath>${'$'}x <inlineCommand>\times</inlineCommand>
                |    <inlineCommand>\frac</inlineCommand>{5}{<inlineCommand>\sqrt</inlineCommand>{3}}${'$'}</inlineMath> in the following equation:
                |    <displayMath>\[
                |       x <displayCommand>\times</displayCommand> <displayCommand>\frac</displayCommand>{5}{<displayCommand>\sqrt</displayCommand>{3}} = y <displayCommand>\cdot</displayCommand> <displayCommand>\max\left</displayCommand>{ 4, <displayCommand>\alpha</displayCommand>, 6 <displayCommand>\right</displayCommand>} +
                |           <displayCommand>\sqrt</displayCommand>[<optionalParam>1234</optionalParam>]{5678}.
                |    \]</displayMath>
                |
                |    \section{More work}\label{sec:moreWork}
                |    A much longer \LaTeXe{} example was written by Henk-Jan~\cite{Gil:02}. But
                |    we can also just do some more epic plugin showoffy stuff like
                |    <displayMath>\begin{align}
                |       <displayCommand>\text</displayCommand>{Stuff here is also highlighted, and also }
                |       <displayCommand>\sum</displayCommand>_{i=0}^n <displayCommand>\left</displayCommand>( i <displayCommand>\right</displayCommand>)
                |    \begin{align}</displayMath>
                |
                |    \section{Results}\label{sec:results}
                |    In this section we describe the results. So basically <inlineMath>${'$'}x${'$'}</inlineMath> but maybe
                |    also <inlineMath>${'$'}<inlineCommand>\hat</inlineCommand>{x}^{2y}${'$'}</inlineMath>.
                |
                |    \section{Conclusions}\label{sec:conclusions}
                |    We worked hard, and achieved very little. Or did we?
                |
                |    % Another extremely descriptive comment.
                |    \bibliographystyle{abbrv}
                |    \bibliography{main}
                |
                |\end{document}
        """.trimMargin()
    }

    /**
     * @author Ruben Schellekens
     */
    object Typography {

        /**
         * Matches each (supported) opening brace to it's opposite close brace.
         */
        @JvmField val braceOpposites = mapOfVarargs(
                "(", ")",
                "[", "]",
                "\\{", "\\}",
                "<", ">",
                "|", "|",
                "\\|", "\\|"
        )
    }

    /**
     * @author Ruben Schellekens
     */
    object Environment {

        @JvmField val listingEnvironments = setOf("itemize", "enumerate", "description")

        /**
         * Map that maps all environments that are expected to have a label to the label prefix they have by convention.
         *
         * environment name `=>` label prefix without colon
         */
        val labeled = mapOfVarargs(
                "figure", "fig",
                "table", "tab",
                "tabular", "tab",
                "equation", "eq",
                "algorithm", "alg"
        )
    }

    /**
     * @author Ruben Schellekens
     */
    object Command {

        /**
         * LaTeX commands that make the text take up more vertical space.
         */
        @JvmField val high = setOf(
                "\\frac", "\\dfrac", "\\sqrt", "\\sum", "\\int", "\\iint", "\\iiint", "\\iiiint",
                "\\prod", "\\bigcup", "\\bigcap", "\\bigsqcup", "\\bigsqcap"
        )

        /**
         * Map that maps all commands that are expected to have a label to the label prefix they have by convention.
         *
         * command name `=>` label prefix without colon
         */
        @JvmField val labeled = mapOfVarargs(
                "\\chapter", "ch",
                "\\section", "sec",
                "\\subsection", "subsec",
                "\\item", "itm"
        )

        /**
         * All commands that represend some kind of reference (think \ref).
         */
        @JvmField val reference = setOf(
                "\\ref", "\\cite", "\\eqref", "\\nameref", "\\autoref",
                "\\fullref", "\\pageref"
        )

        /**
         * All math operators without a leading slash.
         */
        @JvmField val slashlessMathOperators = setOf(
                "arccos", "arcsin", "arctan", "arg", "cos", "cosh", "cot", "coth", "csc",
                "deg", "det", "dim", "exp", "gcd", "hom", "inf", "ker", "lg", "lim", "liminf", "limsup",
                "ln", "log", "max", "min", "Pr", "sec", "sin", "sinh", "sup", "tan", "tanh"
        )

        /**
         * All commands that are able to redefine other commands.
         */
        @JvmField val redefinition = setOf("\\renewcommand", "\\def", "\\let")

        /**
         * Commands for which TeXiFy-IDEA has custom behaviour.
         */
        @JvmField val fragile = setOf(
                "\\addtocounter", "\\begin", "\\chapter", "\\def", "\\documentclass", "\\end",
                "\\include", "\\includeonly", "\\input", "\\label", "\\let", "\\newcommand",
                "\\overline", "\\paragraph", "\\part", "\\renewcommand", "\\section", "\\setcounter",
                "\\sout", "\\subparagraph", "\\subsection", "\\subsubsection", "\\textbf",
                "\\textit", "\\textsc", "\\textsl", "\\texttt", "\\underline", "\\[", "\\]",
                "\\newenvironment", "\\bibitem"
        )

        /**
         * Commands that should not have the given file extensions.
         */
        @JvmField val illegalExtensions = mapOf(
                "\\include" to listOf(".tex"),
                "\\bibliography" to listOf(".bib")
        )

        /**
         * All if commands.
         */
        @JvmField val ifs = setOf(
                "\\if", "\\ifcat", "\\ifnum", "\\ifdim", "\\ifodd", "\\ifvmode", "\\ifhmode", "\\ifmmode",
                "\\ifinner", "\\ifvoid", "\\ifhbox", "\\ifvbox", "\\ifx", "\\ifeof", "\\iftrue", "\\iffalse",
                "\\ifcase", "\\ifdefined", "\\ifcsname", "\\iffontchar", "\\ifincsname", "\\ifpdfprimitive",
                "\\ifpdfabsnum", "\\ifpdfabsdim", "\\ifpdfprimitive", "\\ifprimitive", "\\ifabsum", "\\ifabsdim"
        )

        /**
         * All commands that end if.
         */
        @JvmField val endIfs = setOf("\\fi")

        /**
         * List of all TeX style primitives.
         */
        @JvmField val stylePrimitives = listOf(
                "\\rm", "\\sf", "\\tt", "\\it", "\\sl", "\\sc", "\\bf"
        )

        /**
         * The LaTeX counterparts of all [stylePrimitives] commands where %s is the content.
         */
        @JvmField val stylePrimitveReplacements = listOf(
                "\\textrm{%s}", "\\textsf{%s}", "\\texttt{%s}", "\\textit{%s}",
                "\\textsl{%s}", "\\textsc{%s}", "\\textbf{%s}"
        )

        /**
         * All commands that mark some kind of section.
         */
        @JvmField val sectionMarkers = listOf(
                "\\part", "\\chapter",
                "\\section", "\\subsection", "\\subsubsection",
                "\\paragraph", "\\subparagraph"
        )
    }

    /**
     * @author Ruben Schellekens
     */
    object Pattern {

        @JvmField val ellipsis = Regex("""(?<!\.)(\.\.\.)(?!\.)""")

        /**
         * This is the only correct way of using en dashes.
         */
        @JvmField val correctEnDash = RegexPattern.compile("[0-9]+--[0-9]+")!!

        /**
         * Matches the prefix of a label. Amazing comment this is right?
         */
        @JvmField val labelPrefix = RegexPattern.compile(".*:")!!

        /**
         * Matches the end of a sentence.
         *
         * Includes `[^.][^.]` because of abbreviations (at least in Dutch) like `s.v.p.`
         */
        @JvmField val sentenceEnd = RegexPattern.compile("([^.A-Z][^.A-Z][.?!;;] +[^%])|(^\\. )")!!

        /**
         * Matches all interpunction that marks the end of a sentence.
         */
        @JvmField val sentenceSeperator = RegexPattern.compile("[.?!;;]")!!

        /**
         * Matches when a string ends with a non breaking space.
         */
        @JvmField val endsWithNonBreakingSpace = RegexPattern.compile("~$")!!

        /**
         * Finds all abbreviations that have at least two letters seperated by comma's.
         *
         * It might be more parts, like `b.v.b.d.` is a valid abbreviation. Likewise are `sajdflkj.asdkfj.asdf` and
         * `i.e.`. Single period abbreviations are not being detected as they can easily be confused with two letter words
         * at the end of the sentece (also localisation...) For this there is a quickfix in [LatexLineBreakInspection].
         */
        @JvmField val abbreviation = RegexPattern.compile("[0-9A-Za-z.]+\\.[A-Za-z](\\.|\\s)")!!

        /**
         * Matches leading and trailing whitespace on a string.
         */
        @JvmField val excessWhitespace = RegexPattern.compile("(^(\\s+).*(\\s*)\$)|(^(\\s*).*(\\s+)\$)")!!

        /**
         * Matches a non-ASCII character.
         */
        @JvmField val nonAscii = RegexPattern.compile("\\P{ASCII}")!!

        /**
         * Seperator for multiple parameter values in one parameter.
         *
         * E.g. when you have \cite{citation1,citation2,citation3}, this pattern will match the separating
         * comma.
         */
        @JvmField val parameterSplit = RegexPattern.compile("\\s*,\\s*")!!

        /**
         * Matches the extension of a file name.
         */
        @JvmField val fileExtension = RegexPattern.compile("\\.[a-zA-Z0-9]+$")!!

        /**
         * Matches all leading whitespace.
         */
        @JvmField val leadingWhitespace = RegexPattern.compile("^\\s*")!!

        /**
         * Matches newlines.
         */
        @JvmField val newline = RegexPattern.compile("\\n")!!

        /**
         * Checks if the string is `text`, two newlines, `text`.
         */
        @JvmField val containsMultipleNewlines = RegexPattern.compile("[^\\n]*\\n\\n+[^\\n]*")!!

        /**
         * Matches HTML tags of the form `<tag>`, `<tag/>` and `</tag>`.
         */
        @JvmField val htmlTag = RegexPattern.compile("""<.*?/?>""")!!
    }

    /**
     * @author Ruben Schellekens
     */
    object Package {

        /**
         * All unicode enabling packages.
         */
        @JvmField val unicode = setOf(
                LatexPackage.INPUTENC.with("utf8"),
                LatexPackage.FONTENC.with("T1")
        )
    }

    /**
     * @author Ruben Schellekens
     */
    object Icon {

        /**
         * Maps file extentions to their corresponding icons.
         */
        @JvmField val fileIcons = mapOf(
                "pdf" to TexifyIcons.PDF_FILE,
                "dvi" to TexifyIcons.DVI_FILE,
                "synctex.gz" to TexifyIcons.SYNCTEX_FILE,
                "bbl" to TexifyIcons.BBL_FILE,
                "aux" to TexifyIcons.AUX_FILE,
                "tmp" to TexifyIcons.TEMP_FILE,
                "dtx" to TexifyIcons.DOCUMENTED_LATEX_SOURCE,
                "bib" to TexifyIcons.BIBLIOGRAPHY_FILE,
                "toc" to TexifyIcons.TABLE_OF_CONTENTS_FILE
        )
    }
}
