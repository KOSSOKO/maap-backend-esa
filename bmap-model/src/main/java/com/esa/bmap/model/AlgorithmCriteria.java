package com.esa.bmap.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Capgemini
 *
 */
@JsonInclude(Include.NON_NULL) // ignore nulls during serialization
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class AlgorithmCriteria {

	/**
	 * List of topic and project selected
	 */
	private Map<String, List<String>> listTopicProject = null;
	
	/**
	 * Open input text
	 */
	private String tags;
	
	public AlgorithmCriteria() {
		super();
	}

	/**
	 * 
	 * @param listTopicProject, its a map with the key the topic and a list of project as value
	 * @param tags
	 */
	public AlgorithmCriteria(Map<String, List<String>> listTopicProject, String tags) {
		super();
		this.listTopicProject = listTopicProject;
		this.tags = tags;
	}

	/**
	 * @return the listTopicProject
	 */
	public Map<String, List<String>> getListTopicProject() {
		return listTopicProject;
	}

	/**
	 * @param listTopicProject the listTopicProject to set
	 */
	public void setListTopicProject(Map<String, List<String>> listTopicProject) {
		this.listTopicProject = listTopicProject;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class AlgorithmCriteria {\n");
		sb.append("    listTopicProject: ").append(Utils.toIndentedString(this.listTopicProject)).append("\n");
		sb.append("    tags: ").append(Utils.toIndentedString(this.tags)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	
	
}
