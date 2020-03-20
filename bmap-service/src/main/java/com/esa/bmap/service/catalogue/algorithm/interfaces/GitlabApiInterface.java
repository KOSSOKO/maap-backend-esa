package com.esa.bmap.service.catalogue.algorithm.interfaces;

import java.util.List;
import java.util.Map;

import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Project;

import com.esa.bmap.common.exceptions.BmapException;

public interface GitlabApiInterface {

	/**
	 * Get all projects hosted in Gitlab
	 * @return Gitlab Project
	 */
	public List<Project> getProjects() throws BmapException;
	
	/**
	 * Get a project with it's url
	 * @param url
	 * @return
	 * @throws BmapException
	 */
	public Project getProjectByUrl(String url) throws BmapException;
	
	/**
	 * We extractthe documentation 
	 * @param namespace
	 * @return
	 */
	public Map<String, String> getDocumentationAndConfiguration(Project project) throws BmapException;
	
	
	/**
	 * Get all commits
	 * @return
	 */
	public List<Commit> getCommits(int idProject)  throws BmapException ;
}
