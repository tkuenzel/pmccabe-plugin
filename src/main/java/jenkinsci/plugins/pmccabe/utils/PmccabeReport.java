package jenkinsci.plugins.pmccabe.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.apache.commons.collections.iterators.ArrayListIterator;

import com.google.common.primitives.UnsignedBytes;


public class PmccabeReport implements Serializable {
	private static final long serialVersionUID = 1L;

	private int loc = 0;
	private int mmcc = 0;
	private int tmcc = 0;
	private int nos = 0;
	
    //Procedural Metrics Summary
	ArrayList<PmccabeFunctionSummary> functionsSummaryList = new ArrayList<PmccabeFunctionSummary>();

    public void addFunctionSummary(PmccabeFunctionSummary summary) {
    	loc += summary.getLinesInFunction();
    	mmcc += summary.getModifiedMcCabeCyclomaticComplexity();
    	tmcc += summary.getTraditionalMcCabeCyclomaticComplexity();
    	nos += summary.getStatementsInFunction();

    	functionsSummaryList.add(summary);
    }
    
    public List<PmccabeFunctionSummary> getFunctionSummaryList() {
		return functionsSummaryList;
	}
    
    public int getLinesOfCode() {
    	return loc;
    }
    
    /**
     * @return Total number of functions or methods scanned by pmccabe.
     */
    public int getNumberOfFunctions() {
    	return functionsSummaryList.size();
	}
    
    /**
     * @return The summary of Modified McCabe Cyclomatic Complexity of functions or methods scanned by pmccabe.
     */
    public int getModifiedComplexity() {
    	return mmcc;
    }
    
    /**
     * @return The summary of Traditional McCabe Cyclomatic Complexity of functions or methods scanned by pmccabe.
     */
    public int getTraditionalComplexity() {
    	return tmcc;
    }
    
    /**
     * @return The number of statements of functions or methods scanned by pmccabe.
     */
    public int getNumberOfStatements() {
    	return nos;
    }
    
    /**
     * @return The number of files scanned by pmccabe.
     */
    public int getNumberOfFiles() {
    	HashSet<String> files = new HashSet<String>();
    	
    	for (ListIterator<PmccabeFunctionSummary> i = functionsSummaryList.listIterator(); i.hasNext(); ) {
    		files.add(i.next().getFilename());
    	}
    	
    	return files.size();
    }
    
    /**
     * @return The average of Modified McCabe Cyclomatic Complexity per function.
     */
    public float getModifiedComplexityAverage() {
    	if (functionsSummaryList.size() == 0)
    		return 0;
    	else
    		return (float)mmcc / (float)functionsSummaryList.size();
    }

    /**
     * @return The average of Traditional McCabe Cyclomatic Complexity per function.
     */
    public float getTraditionalComplexityAverage() {
    	if (functionsSummaryList.size() == 0)
    		return 0;
    	else
    		return (float)tmcc / (float)functionsSummaryList.size();
    }
    
    /**
     * @return The average of lines of code per function.
     */
    public float getLocAverage() {
    	if (functionsSummaryList.size() == 0)
    		return 0;
    	else
    		return (float)loc / (float)functionsSummaryList.size();
    }
}
