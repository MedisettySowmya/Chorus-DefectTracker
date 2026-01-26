package DataModels;

import java.util.Date;

public class DefectData {

	// Defining all fields from the Excel sheet
	private String project;
	private String bugId;
	private String defectTitle;
	private String sprintId;
	private String devResource;
	private String foundBy;
	private String defectCategory;
	private String description;
	private String pbiId;
	private String defectType;
	private String foundOn;
	private String fixProvidedBy;
	private String assignedTo;
	private String severity;
	private String defectStatus;
	private String environment;
	private String ownedBy;
	private String fixProvidedOn;
	private String steps;
	private String expectedResult;
	private String actualResult;

	// Default Constructor
	public DefectData() {

	}

	// Getters and Setters for each field

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getBugId() {
		return bugId;
	}

	public void setBugId(String bugId) {
		this.bugId = bugId;
	}

	public String getDefectTitle() {
		return defectTitle;
	}

	public void setDefectTitle(String defectTitle) {
		this.defectTitle = defectTitle;
	}

	public String getSprintId() {
		return sprintId;
	}

	public void setSprintId(String sprintId) {
		this.sprintId = sprintId;
	}

	public String getDevResource() {
		return devResource;
	}

	public void setDevResource(String devResource) {
		this.devResource = devResource;
	}

	public String getFoundBy() {
		return foundBy;
	}

	public void setFoundBy(String foundBy) {
		this.foundBy = foundBy;
	}

	public String getDefectCategory() {
		return defectCategory;
	}

	public void setDefectCategory(String defectCategory) {
		this.defectCategory = defectCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPbiId() {
		return pbiId;
	}

	public void setPbiId(String pbiId) {
		this.pbiId = pbiId;
	}

	public String getDefectType() {
		return defectType;
	}

	public void setDefectType(String defectType) {
		this.defectType = defectType;
	}

	public String getFoundOn() {
		return foundOn;
	}

	public void setFoundOn(String foundOn) {
		this.foundOn = foundOn;
	}

	public String getFixProvidedBy() {
		return fixProvidedBy;
	}

	public void setFixProvidedBy(String fixProvidedBy) {
		this.fixProvidedBy = fixProvidedBy;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDefectStatus() {
		return defectStatus;
	}

	public void setDefectStatus(String defectStatus) {
		this.defectStatus = defectStatus;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(String ownedBy) {
		this.ownedBy = ownedBy;
	}

	public String getFixProvidedOn() {
		return fixProvidedOn;
	}

	public void setFixProvidedOn(String fixProvidedOn) {
		this.fixProvidedOn = fixProvidedOn;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	public String getActualResult() {
		return actualResult;
	}

	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}
}
