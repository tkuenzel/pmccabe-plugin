package jenkinsci.plugins.pmccabe.utils;

import hudson.FilePath;
import hudson.remoting.VirtualChannel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;



public class PmccabeFileParser implements FilePath.FileCallable<PmccabeReport> {
    private static final Logger LOGGER = Logger.getLogger(PmccabeFileParser.class.getName());

    private FilePath resultFilePath;

    public PmccabeFileParser(FilePath resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public PmccabeReport invoke(java.io.File dir, VirtualChannel channel) throws IOException {

    	PmccabeReport report = new PmccabeReport();
    	
    	try {
    		FileInputStream fis = new FileInputStream(new File(resultFilePath.toURI()));
    		BufferedReader reader = new BufferedReader(new InputStreamReader(fis, "UTF-8"));

        	String line;
        	
    		while (null != (line = reader.readLine())) {
    			PmccabeFunctionSummary summary = parseLine(line);
    			if (null != summary)
    				report.addFunctionSummary(summary);
    		}
    			   		
    		reader.close();
    		fis.close();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Parsing file error :" + e.toString());
            throw new RuntimeException("Parsing file error :" + e.toString());
        }

        return report;
    }
    
    private PmccabeFunctionSummary parseLine(final String line) {
    	String[] elements = line.split("\\s+");
    	
    	if (elements.length != 7) {
    		LOGGER.log(Level.SEVERE, "Number of found elements in the line are wrong - " + elements.length);
    		return null;
    	}
    	
    	PmccabeFunctionSummary summary = new PmccabeFunctionSummary();

    	summary.setModifiedMcCabeCyclomaticComplexity(Integer.parseInt(elements[0]));
    	summary.setTraditionalMcCabeCyclomaticComplexity(Integer.parseInt(elements[1]));
    	summary.setStatementsInFunction(Integer.parseInt(elements[2]));
    	summary.setFirstLineOfFunction(Integer.parseInt(elements[3]));
    	summary.setLinesInFunction(Integer.parseInt(elements[4]));

    	String fname[] = elements[5].split("\\(\\d+\\)");
    	summary.setFilename(fname[0]);

    	summary.setName(elements[6]);
    	
		return summary;
	}
}
