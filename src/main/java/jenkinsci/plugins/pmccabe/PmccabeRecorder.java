package jenkinsci.plugins.pmccabe;

import hudson.FilePath;
import hudson.Launcher;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.model.AbstractBuild;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Recorder;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;

import jenkinsci.plugins.pmccabe.utils.PmccabeFileParser;
import jenkinsci.plugins.pmccabe.utils.PmccabeReport;

import org.kohsuke.stapler.DataBoundConstructor;


public class PmccabeRecorder extends Recorder implements Serializable {
	private final String outputFilePath;
	private boolean modifiedComplexity;
	private static final long serialVersionUID = 1L;

    @DataBoundConstructor
    public PmccabeRecorder(String outputFilePath, boolean modifiedComplexity) {
        this.outputFilePath = outputFilePath;
        this.modifiedComplexity = modifiedComplexity;
    }


	public BuildStepMonitor getRequiredMonitorService() {
		// TODO Auto-generated method stub
		return BuildStepMonitor.NONE;
	}

	public boolean isModifiedComplexity() {
		return modifiedComplexity;
	}

	@Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {

		listener.getLogger().println("Parsing pmccabe results");

//		FilePath workspace = build.getWorkspace();
		PrintStream logger = listener.getLogger();

		FilePath metricFile = new FilePath(build.getWorkspace(), outputFilePath);
		try {
			if (!metricFile.exists()) {
				listener.getLogger().println(String.format("The given '%s' metric path doesn't exist.", outputFilePath));
				build.setResult(Result.FAILURE);
				return false;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace(logger);
			build.setResult(Result.FAILURE);
			return false;

		} catch (InterruptedException ie) {
			ie.printStackTrace(logger);
			build.setResult(Result.FAILURE);
			return false;
		} catch (Throwable t) {
			t.printStackTrace(logger);
			build.setResult(Result.FAILURE);
			return false;
		}

		PmccabeFileParser parser = new PmccabeFileParser(metricFile);
		
		try {
			PmccabeReport report = parser.parse();
	        build.addAction(new PmccabeAction(build, report));

		} catch (IOException ioe) {
			ioe.printStackTrace(logger);
			build.setResult(Result.FAILURE);
			return false;
		}
		
		listener.getLogger().println("End Processing pmccabe results");
		return true;
	}
	
    public String getOutputFilePath() {
        return outputFilePath;
    }

}

