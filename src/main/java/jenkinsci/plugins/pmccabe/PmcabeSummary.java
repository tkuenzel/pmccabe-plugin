package jenkinsci.plugins.pmccabe;

import jenkinsci.plugins.pmccabe.utils.PmccabeReport;



public class PmcabeSummary {

    private PmcabeSummary() {
        super();
    }

    public static String createReportSummaryDetails(PmccabeReport report, PmccabeReport previous) {
        StringBuilder builder = new StringBuilder();

        if (report == null) {
            return "";
        }

        builder.append("<li>");
        builder.append("Number of files :");
        builder.append(report.getNumberOfFiles());
        if (previous != null) {
            printDifference(report.getNumberOfFiles(), previous.getNumberOfFiles(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append("Lines of code :");
        builder.append(report.getLinesOfCode());
        if (previous != null) {
            printDifference(report.getLinesOfCode(), previous.getLinesOfCode(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append("McCabe's Traditional Cyclomatic Complexity :");
        builder.append(report.getTraditionalComplexity());
        if (previous != null) {
            printDifference(report.getTraditionalComplexity(), previous.getTraditionalComplexity(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append("McCabe's Modified Cyclomatic Complexity :");
        builder.append(report.getModifiedComplexity());
        if (previous != null) {
            printDifference(report.getModifiedComplexity(), previous.getModifiedComplexity(), builder);
        }
        builder.append("</li>");
        builder.append("<li>");
        builder.append("Number of functions :");
        builder.append(report.getNumberOfFunctions());
        if (previous != null) {
            printDifference(report.getNumberOfFunctions(), previous.getNumberOfFunctions(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append("Number of statements :");
        builder.append(report.getNumberOfStatements());
        if (previous != null) {
            printDifference(report.getNumberOfStatements(), previous.getNumberOfFunctions(), builder);
        }
        builder.append("</li>");


        builder.append("<li>");
        builder.append("Average of lines of code :");
        builder.append(report.getLocAverage());
        if (previous != null) {
            printDifference(report.getLocAverage(), previous.getLocAverage(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append("Average of McCabe's Traditional Cyclomatic Complexity :");
        builder.append(report.getTraditionalComplexityAverage());
        if (previous != null) {
            printDifference(report.getTraditionalComplexityAverage(), previous.getTraditionalComplexityAverage(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append("Average of McCabe's Modified Cyclomatic Complexity :");
        builder.append(report.getModifiedComplexityAverage());
        if (previous != null) {
            printDifference(report.getModifiedComplexityAverage(), previous.getModifiedComplexityAverage(), builder);
        }
        builder.append("</li>");

        return builder.toString();
    }

    private static void printDifference(float current, float previous, StringBuilder builder) {
        float difference = current - previous;
        builder.append(" (");

        if (difference >= 0) {
            builder.append('+');
        }
        builder.append(difference);
        builder.append(")");
    }
}
