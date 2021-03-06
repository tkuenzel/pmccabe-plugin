package jenkinsci.plugins.pmccabe;


import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import jenkinsci.plugins.pmccabe.util.Messages;

@Extension
public class PmccabeRecoderDescriptor extends BuildStepDescriptor<Publisher> {

    public PmccabeRecoderDescriptor() {
        super(PmccabeRecorder.class);
    }

	@Override
	public boolean isApplicable(Class<? extends AbstractProject> jobType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getDisplayName() {
		return Messages.PmccabePublisher_Name();
	}

}
