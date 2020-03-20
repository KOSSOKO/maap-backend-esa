package com.esa.bmap.service.catalogue.algorithm.implement;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;
import org.gitlab4j.api.models.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.service.catalogue.algorithm.interfaces.GitlabApiInterface;
import com.google.common.base.Charsets;

@Service(value = "GitlabApiInterface")
public class GitlabServiceImpl implements GitlabApiInterface {

	private static final String GITLABAPI_INIT_MESSAGE = "gitlabapi.init";

	private final Logger LOG = LoggerFactory.getLogger(GitlabServiceImpl.class);
	
	private GitLabApi gitLabApi;
		
	/**
	 * Token for the authentication to the gitlab server to use the api of gitlab
	 */
	@Value("${gitlab.server.admin.token}")
	private String gitlabToken;
	
	@Value("${gitlab.server.url}")
	private String gitlabUrl;
	
	@Value("${gitlab.server.url.level}")
	private String levelInfo;
	
	/**
	 * Default Constructor
	 */
	public GitlabServiceImpl() {

	}
	
	/**
	 * Initialize the gitlab api client wiht the properties gitlabUrl and gitlabToken
	 */
	@PostConstruct
	public void initGitlabApi() {
		LOG.info(Common.getMessageValue(GITLABAPI_INIT_MESSAGE),gitlabUrl);
		gitLabApi = new GitLabApi(gitlabUrl, gitlabToken);
		// Log using the shared logger and the INFO level
		//We init the log leve
		Level level = Level.parse(levelInfo);
		gitLabApi.enableRequestResponseLogging(level);
	}

	@Override
	public List<Project> getProjects() throws BmapException {
		// Get the list of projects your account has access to
		LOG.info("Liste of projects");
		List<Project> projects;
		try {
			projects = gitLabApi.getProjectApi().getProjects();
			LOG.info("Liste of projects get from gitlab "+projects.size());
		} catch (GitLabApiException e) {
			LOG.debug(e.toString());
			throw new BmapException(e.getMessage(), HttpStatus.valueOf(e.getHttpStatus()));
		}
		
		return projects;
	}

	@Override
	public Project getProjectByUrl(String url) throws BmapException {
		//We get all projects, then we filter
		List<Project> projects = this.getProjects();
		LOG.info("Number of projet found in Gitlab : " + projects.size());
		Project project = null;
		//We iterate till we found the correct url
		for (Project p : projects) {
			if(p.getHttpUrlToRepo().equals(url)) {
				project = p;
				LOG.info("Projet found in Gitlab : " + p.getHttpUrlToRepo() + " and id is = "+p.getId());
				break;
			}
		}

		return project;
	}
	
	/**
	 * We extracte the documentation 
	 * @param namespace
	 * @return map containing the documentation and configuration file in String Map
	 * @throws BmapException 
	 */
	public Map<String, String> getDocumentationAndConfiguration(Project project) throws BmapException {
		Map<String, String> docAndConf = new HashMap<String, String>();
		
		//We set the documentation
		try {
			InputStream ipDocumentation = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "doc/documentation.md");
			InputStream ipConfiguration = gitLabApi.getRepositoryFileApi().getRawFile(project.getId(), "master", "conf/configuration.properties");
			String documentation = IOUtils.toString(ipDocumentation, Charsets.UTF_8); 
			String configuration = IOUtils.toString(ipConfiguration, Charsets.UTF_8); 
			docAndConf.put(AlgorithmServiceImpl.DOCUMENTATION, documentation);
			docAndConf.put(AlgorithmServiceImpl.CONFIGURATION, configuration);
		} catch (GitLabApiException | IOException e) {
			LOG.debug(e.toString());
			throw new BmapException(e.toString());
		}
		//We return the map
		return docAndConf;
	}

	@Override
	public List<Commit> getCommits(int idProject)  throws BmapException  {
		try {
			return gitLabApi.getCommitsApi().getCommits(idProject);
		} catch (GitLabApiException e) {
			LOG.debug(e.toString());
			throw new BmapException(e.toString());
		}
	}
}