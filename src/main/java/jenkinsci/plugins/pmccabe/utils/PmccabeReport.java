package jenkinsci.plugins.pmccabe.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.iterators.ArrayListIterator;

import com.google.common.primitives.UnsignedBytes;


public class PmccabeReport implements Serializable {
	private static final long serialVersionUID = 1L;
	
    //Procedural Metrics Summary
	ArrayList<PmccabeFunctionSummary> functionsSummaryList = new ArrayList<PmccabeFunctionSummary>();

    public void addFunctionSummary(PmccabeFunctionSummary summary) {
    	functionsSummaryList.add(summary);
    }
    
    public List<PmccabeFunctionSummary> getFunctionSummaryList() {
		return functionsSummaryList;
	}
    
    public int getLinesOfCode() {
    	int loc = 0;
    	for (ListIterator<PmccabeFunctionSummary> i = functionsSummaryList.listIterator(); i.hasNext(); ) {
    		loc += i.next().getLinesInFunction();
    	}
    	
    	return loc;
    }
}
