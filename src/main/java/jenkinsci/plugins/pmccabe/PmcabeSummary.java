package jenkinsci.plugins.pmccabe;

import jenkinsci.plugins.pmccabe.utils.PmccabeReport;
import jenkinsci.plugins.pmccabe.util.Messages;



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
        builder.append(Messages.PmccabeAction_LinesOfCode() + " :");
        builder.append(report.getLinesOfCode());
        if (previous != null) {
            printDifference(report.getLinesOfCode(), previous.getLinesOfCode(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append(Messages.PmccabeAction_TraditionalComplexity() + " :");
        builder.append(report.getTraditionalComplexity());
        if (previous != null) {
            printDifference(report.getTraditionalComplexity(), previous.getTraditionalComplexity(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append(Messages.PmccabeAction_ModifiedComplexity() + " :");
        builder.append(report.getModifiedComplexity());
        if (previous != null) {
            printDifference(report.getModifiedComplexity(), previous.getModifiedComplexity(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append(Messages.PmccabeAction_NumberOfFiles() + " :");
        builder.append(report.getNumberOfFiles());
        if (previous != null) {
            printDifference(report.getNumberOfFiles(), previous.getNumberOfFiles(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append(Messages.PmccabeAction_NumberOfFunctions() + " :");
        builder.append(report.getNumberOfFunctions());
        if (previous != null) {
            printDifference(report.getNumberOfFunctions(), previous.getNumberOfFunctions(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append(Messages.PmccabeAction_NumberOfStatements() + " :");
        builder.append(report.getNumberOfStatements());
        if (previous != null) {
            printDifference(report.getNumberOfStatements(), previous.getNumberOfFunctions(), builder);
        }
        builder.append("</li>");


        builder.append("<li>");
        builder.append(Messages.PmccabeAction_LinesOfCodeAverage() + " :");
        builder.append(report.getLocAverage());
        if (previous != null) {
            printDifference(report.getLocAverage(), previous.getLocAverage(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append(Messages.PmccabeAction_TraditionalComplexityAverage() + " :");
        builder.append(report.getTraditionalComplexityAverage());
        if (previous != null) {
            printDifference(report.getTraditionalComplexityAverage(), previous.getTraditionalComplexityAverage(), builder);
        }
        builder.append("</li>");

        builder.append("<li>");
        builder.append(Messages.PmccabeAction_ModifiedComplexityAverage() + " :");
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
