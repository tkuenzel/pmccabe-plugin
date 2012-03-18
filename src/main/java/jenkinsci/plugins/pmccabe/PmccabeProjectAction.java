package jenkinsci.plugins.pmccabe;

import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.Action;
import hudson.util.ChartUtil;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;


public class PmccabeProjectAction implements Action, Serializable {

	public static final String URL_NAME = "PmccabeReport";

    public static final int CHART_WIDTH = 500;
    public static final int CHART_HEIGHT = 200;

    public AbstractProject<?, ?> project;

    public PmccabeProjectAction(final AbstractProject<?, ?> project) {
        this.project = project;
    }

    public String getIconFileName() {
        return "/plugin/pmccabe/icons/pmccabe-24.png";
    }

    public String getDisplayName() {
        return "PMcCabe Results";
    }

    public String getUrlName() {
        return URL_NAME;
    }


    /**
     * Redirects the index page to the last result.
     *
     * @param request  Stapler request
     * @param response Stapler response
     * @throws IOException in case of an error
     */
    public void doIndex(final StaplerRequest request, final StaplerResponse response) throws IOException {
        AbstractBuild<?, ?> build = getLastFinishedBuild();
        if (build != null) {
            response.sendRedirect2(String.format("../%d/%s", build.getNumber(), PmccabeAction.URL_NAME));
        }
    }

    /**
     * Returns the last finished build.
     *
     * @return the last finished build or <code>null</code> if there is no
     *         such build
     */
    public AbstractBuild<?, ?> getLastFinishedBuild() {
        AbstractBuild<?, ?> lastBuild = project.getLastBuild();
        while (lastBuild != null && (lastBuild.isBuilding() || lastBuild.getAction(PmccabeAction.class) == null)) {
            lastBuild = lastBuild.getPreviousBuild();
        }
        return lastBuild;
    }

    public final boolean hasValidResults() {
        AbstractBuild<?, ?> build = getLastFinishedBuild();
        if (build != null) {
            PmccabeAction resultAction = build.getAction(PmccabeAction.class);
            if (resultAction != null) {
                return resultAction.getPreviousReport() != null;
            }
        }
        return false;
    }

    /**
     * Display the graph
     *
     * @param request  Stapler request
     * @param response Stapler response
     * @throws IOException in case of an error in
     */
    @SuppressWarnings("unused")
    public void doGraph(final StaplerRequest request, final StaplerResponse response) throws IOException {
        AbstractBuild<?, ?> lastBuild = this.getLastFinishedBuild();
        PmccabeAction lastAction = lastBuild.getAction(PmccabeAction.class);

        if (ChartUtil.awtProblemCause != null) {
            response.sendRedirect2(request.getContextPath() + "/images/headless.png");
            return;
        }

        Calendar timestamp = lastBuild.getTimestamp();

        if (request.checkIfModified(timestamp, response)) return;


        (new PmccabeChartBuilder(lastAction, CHART_WIDTH, CHART_HEIGHT)).doPng(request, response);
    }


}
