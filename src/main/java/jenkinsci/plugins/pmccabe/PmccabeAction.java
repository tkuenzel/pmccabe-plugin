package jenkinsci.plugins.pmccabe;

import hudson.model.AbstractBuild;
import hudson.model.Action;
import org.kohsuke.stapler.StaplerProxy;

import java.io.Serializable;


public class PmccabeAction implements Action {

    public static final String URL_NAME = "PmccabeReport";

    private final AbstractBuild build;
    private int value;

    public PmccabeAction(AbstractBuild build, int value) {
        this.build = build;
        this.value = value;
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

    @SuppressWarnings("unused")
    public String getSummary() {
    	return "Here getSummary()";
    }

    @SuppressWarnings("unused")
    public String getDetails() {
    	return "Here getDetails()";
    }

    public AbstractBuild getBuild() {
        return build;
    }
    
    public int getValue() {
    	return value;
    }
}
