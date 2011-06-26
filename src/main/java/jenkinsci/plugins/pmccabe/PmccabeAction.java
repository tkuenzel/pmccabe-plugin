package jenkinsci.plugins.pmccabe;

import hudson.model.Action;
import hudson.model.AbstractBuild;
import jenkinsci.plugins.pmccabe.utils.PmccabeReport;
import jenkinsci.plugins.pmccabe.util.Messages;

public class PmccabeAction implements Action {

    public static final String URL_NAME = "PmccabeReport";

    private AbstractBuild<?, ?> build;
    private PmccabeReport report;

	public PmccabeAction(AbstractBuild<?, ?> build, PmccabeReport report) {
        this.build = build;
        this.report = report;
    }

    public String getIconFileName() {
        return "/plugin/pmccabe/icons/pmccabe-24.png";
    }

    public String getDisplayName() {
        return Messages.PmccabeAction_PmccabeReport();
    }

    public String getUrlName() {
        return URL_NAME;
    }

    @SuppressWarnings("unused")
    public String getDetails() {
    	return PmcabeSummary.createReportSummaryDetails(report, getPreviousReport());
    }

    public AbstractBuild<?, ?> getBuild() {
        return build;
    }
    
    public PmccabeReport getReport() {
        return report;
    }

	public Object getTarget() {
		return report;
	}
	
	public boolean isLastBuild() {
		return null == build.getNextBuild();
	}
	
    private PmccabeReport getPreviousReport() {
        PmccabeAction previousAction = this.getPreviousAction();
        PmccabeReport previousReport = null;
        if (previousAction != null) {
            previousReport = previousAction.getReport();
        }

        return previousReport;
    }

    private PmccabeAction getPreviousAction() {
        AbstractBuild<?, ?> previousBuild = this.build.getPreviousBuild();
        if (previousBuild != null) {
            return previousBuild.getAction(PmccabeAction.class);
        }
        return null;
    }

}
