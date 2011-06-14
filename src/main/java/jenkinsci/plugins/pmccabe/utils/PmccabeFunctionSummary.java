package jenkinsci.plugins.pmccabe.utils;

import java.io.Serializable;

public class PmccabeFunctionSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String name;

    private String filename;
    
    private int linesInFunction;

    private int traditionalMcCabeCyclomaticComplexity;

    private int modifiedMcCabeCyclomaticComplexity;

    private int statementsInFunction;
    
    private int firstLineOfFunction;

	public void setFirstLineOfFunction(int firstLineOfFunction) {
		this.firstLineOfFunction = firstLineOfFunction;
	}

	public int getFirstLineOfFunction() {
		return firstLineOfFunction;
	}

	public void setStatementsInFunction(int statementsInFunction) {
		this.statementsInFunction = statementsInFunction;
	}

	public int getStatementsInFunction() {
		return statementsInFunction;
	}

	public void setModifiedMcCabeCyclomaticComplexity(
			int modifiedMcCabeCyclomaticComplexity) {
		this.modifiedMcCabeCyclomaticComplexity = modifiedMcCabeCyclomaticComplexity;
	}

	public int getModifiedMcCabeCyclomaticComplexity() {
		return modifiedMcCabeCyclomaticComplexity;
	}

	public void setTraditionalMcCabeCyclomaticComplexity(
			int traditionalMcCabeCyclomaticComplexity) {
		this.traditionalMcCabeCyclomaticComplexity = traditionalMcCabeCyclomaticComplexity;
	}

	public int getTraditionalMcCabeCyclomaticComplexity() {
		return traditionalMcCabeCyclomaticComplexity;
	}

	public void setLinesInFunction(int linesInFunction) {
		this.linesInFunction = linesInFunction;
	}

	public int getLinesInFunction() {
		return linesInFunction;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
