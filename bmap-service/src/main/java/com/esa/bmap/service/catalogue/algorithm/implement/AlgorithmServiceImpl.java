package com.esa.bmap.service.catalogue.algorithm.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.gitlab4j.api.models.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.common.exceptions.ExceptionErrorType;
import com.esa.bmap.dao.catalogue.algorithm.interfaces.AlgorithmRepository;
import com.esa.bmap.dao.catalogue.algorithm.interfaces.ExecutableRepository;
import com.esa.bmap.model.Algorithm;
import com.esa.bmap.model.AlgorithmCriteria;
import com.esa.bmap.model.Executable;
import com.esa.bmap.model.Privacy;
import com.esa.bmap.model.Status;
import com.esa.bmap.service.catalogue.algorithm.interfaces.AlgorithmServiceInterface;
import com.esa.bmap.service.catalogue.algorithm.interfaces.GitlabApiInterface;

@Service(value = "AlgorithmServiceInterface")
public class AlgorithmServiceImpl implements AlgorithmServiceInterface {

	Logger log = LoggerFactory.getLogger(AlgorithmServiceImpl.class);

	@Autowired
	private AlgorithmRepository algorithmRepository;

	@Autowired
	private GitlabApiInterface gitlabService;

	@Autowired
	private ExecutableRepository executableRepository;

	public static final String DOCUMENTATION = "documentation";
	public static final String CONFIGURATION = "configuration";

	/**
	 * get all algorithms by the project id
	 * 
	 * @param idProject
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Collection<Algorithm> getAlgorithmsByProject(Long idProject) throws BmapException {
		return null;
	}

	/**
	 * get all the algorithms from the database and Gitlab
	 * 
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Collection<Algorithm> getAllAlgorithms() throws BmapException {

		log.info("Get all algorithms");
		// First we get all of the algorithms from the data base
		List<Algorithm> listOfAlgorithm = new ArrayList<>();

		try {
			this.algorithmRepository.findAll().forEach(listOfAlgorithm::add);

			// We geta all of the information about the algorithms from gitlab
			List<Project> listProject = gitlabService.getProjects();

			// We update the information to have fresh information from Gitlab
			setAlgorithmWithGitlabData(listOfAlgorithm, listProject);

			log.info("Set all algorithms information with algo founded in Gitlab");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return listOfAlgorithm;
	}

	/**
	 * get an algorithm using his id
	 * 
	 * @param idAlgorithm
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Algorithm getAlgorithm(int idAlgorithm) throws BmapException {
		Optional<Algorithm> algo = null;
		List<Algorithm> algoList = new ArrayList<Algorithm>();

		try {
			algo = algorithmRepository.findById(idAlgorithm);

			// We set all of the information provided by gitlab
			// We add this algo in a list
			if (algo.isPresent()) {
				algoList.add(algo.get());
				this.algorithmRepository.findAll().forEach(algoList::add);

				// We geta all of the information about the algorithms from gitlab
				List<Project> listProject = gitlabService.getProjects();

				// We update the information to have fresh information from Gitlab
				setAlgorithmWithGitlabData(algoList, listProject);
			}

			if (!algoList.isEmpty()) {
				// We return the unique and first element
				return algoList.get(0);
			} else {
				return null;
			}

		} catch (Exception e) {

			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * get an algorithm using it's name
	 * 
	 * @param name
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Algorithm getAlgorithmByName(String name) throws BmapException {

		Algorithm algo = null;
		try {

			Collection<Algorithm> listAlgo = this.getAllAlgorithms();

			for (Algorithm algoTmp : listAlgo) {

				if (algoTmp.getName().equals(name)) {
					algo = algoTmp;
					break;
				}
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return algo;
	}

	/**
	 * get list of all algorithm that have the same status
	 * 
	 * @param status
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Collection<Algorithm> getAlgorithmsByStatus(Status status) throws BmapException {

		List<Algorithm> listOfAlgorithm = new ArrayList<>();

		try {
			this.algorithmRepository.findByStatus(status).forEach(listOfAlgorithm::add);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return listOfAlgorithm;
	}

	/**
	 * search algorithms from the database
	 * 
	 * @throws BmapException
	 */
	@Override
	public Collection<Algorithm> searchAlgorithm(String criteria) throws BmapException {

		List<Algorithm> listOfAlgorithm = null;
		try {
			// We call the repository to execute the request
			Collection<Algorithm> algoCollection = this.algorithmRepository.findAlgorithmsByCriteria(criteria);
			listOfAlgorithm = new ArrayList<>();
			if (algoCollection instanceof List) {
				listOfAlgorithm = (ArrayList<Algorithm>) algoCollection;
			} else {
				listOfAlgorithm = new ArrayList<Algorithm>(algoCollection);
			}

			this.algorithmRepository.findAll().forEach(listOfAlgorithm::add);

			// We geta all of the information about the algorithms from gitlab
			List<Project> listProject = gitlabService.getProjects();

			// We update the information to have fresh information from Gitlab
			setAlgorithmWithGitlabData(listOfAlgorithm, listProject);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return listOfAlgorithm;
	}

	/**
	 * add an algorithm to the database
	 * 
	 * @param algorithm
	 * @return
	 * @throws BmapException
	 */
	public Algorithm addAlgorithm(Algorithm algorithm) throws BmapException {

		try {
			// First of all we get the project in gitlab
			log.info("Start searching the algo in gitlab ");
			Project gitlabProj = gitlabService.getProjectByUrl(algorithm.getGitUrl());
			log.info("The algo is found " + gitlabProj.getId());
			// We check if the algorithm is in the public repository
			if (getTopicAndProjectWithNamspace(gitlabProj.getPathWithNamespace().toString()).size() < 2) {
				// We raise an error
				log.error("The project is not in the public repository");
				throw new BmapException("The project is not in the public repository", HttpStatus.NOT_ACCEPTABLE);
			}
			// We get the Executable and we save it
			Executable exe = algorithm.getExecutable();
			// We set the name of the docker image with the name of the algo
			log.info(gitlabProj.getName());
			exe.setName(gitlabProj.getName());
			exe = executableRepository.save(exe);
			algorithm.setExecutable(exe);

			// We set the id to have exactly the same

			algorithm.setAlgorithmId(gitlabProj.getId());
			algorithm.setName(gitlabProj.getName());
			// We set the algorithm status and privacy
			algorithm.setStatusEnum(Status.SHARED);
			algorithm.setPrivacy(Privacy.PUBLIC);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		// We save the algorithm in ou database
		return algorithmRepository.save(algorithm);
	}

	/**
	 * We set all the information with information sent by Gitlab
	 * 
	 * @param listAlgo list of algorithms retrieved from the database
	 * @param listProject list of the algorithms (repository gitlab) retrieved from
	 *            the Gitlab
	 * @throws BmapException
	 */
	private void setAlgorithmWithGitlabData(List<Algorithm> listAlgo, List<Project> listProject) throws BmapException {

		try {
			// We iterate and compare
			for (Algorithm algo : listAlgo) {
				for (Project proj : listProject) {
					if (algo.getId().equals(proj.getId())) {
						algo.setName(proj.getName());
						algo.setDescription(proj.getDescription());
						algo.setGitUrl(proj.getWebUrl());
						algo.setGitUrlSource(proj.getHttpUrlToRepo());
						// We get the tags
						if (!proj.getTagList().isEmpty()) {
							String tags = proj.getTagList().toString().replaceAll(",", " ");
							algo.setTags(tags);
						}

						algo.setLastUpdateDate(proj.getLastActivityAt().toString());
						// We get the name of the topic and the project from the namespace
						Map<String, String> topicProject = this
								.getTopicAndProjectWithNamspace(proj.getPathWithNamespace());
						algo.setTopic(topicProject.get("topic"));
						algo.setProject(topicProject.get("project"));
						// We get the documentation and the configuration file
						Map<String, String> docAndConf = gitlabService.getDocumentationAndConfiguration(proj);
						// We set the algorithm and convert doc to html format
						Parser parser = Parser.builder().build();
						Node document = parser.parse(docAndConf.get(DOCUMENTATION));
						HtmlRenderer renderer = HtmlRenderer.builder().build();
						renderer.render(document); // "<p>This is <em>Sparta</em></p>\n"
						algo.setDocumentation(renderer.render(document));
						// document = parser.parse(docAndConf.get(configuration));
						algo.setConfiguration(docAndConf.get(CONFIGURATION).replaceAll("(\r\n|\n)", "<br />"));
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * We extract the project and the topic with the namespace sent by gitlab
	 * 
	 * @param namespace
	 * @return
	 */
	private Map<String, String> getTopicAndProjectWithNamspace(String namespace) throws BmapException {
		Map<String, String> topicAndProject = new HashMap<String, String>();

		try {
			// We extract the information : bmap/RADAR/Geometry/SlrToGrdProj
			// We should get RADAR and geometry

			String[] dataNamespace = namespace.split("/");
			if (dataNamespace.length >= 3) {
				topicAndProject.put("topic", dataNamespace[1]);
				topicAndProject.put("project", dataNamespace[2]);
			} else {
				throw new BmapException("The project is not in the public repository", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		// We return the map
		return topicAndProject;
	}

	@Override
	public Algorithm updateStatus(int idAlgo, Status status) throws BmapException {

		try {
			Algorithm algorithm = getAlgorithm(idAlgo);
			algorithm.setStatusEnum(status);
			return algorithmRepository.save(algorithm);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * TODO: validate the process of deletion of an algo delete an algorithm
	 * 
	 * @param idAlgo
	 * @throws BmapException
	 */
	@Override
	public void deleteAlgorithm(int idAlgo) throws BmapException {
		try {
			this.algorithmRepository.deleteById(idAlgo);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	/**
	 * update an algorithm
	 * 
	 * @param algorithm
	 * @return
	 */
	public Algorithm updateAlgorithm(Algorithm algorithm) {
		return this.algorithmRepository.save(algorithm);
	}

	@Override
	public Collection<Algorithm> getLastTenAlgorithmsPublished() throws BmapException {
		List<Algorithm> listOfAlgo = null;
		try {
			listOfAlgo = this.algorithmRepository.findTenLastAlgo(PageRequest.of(0, 10, Sort.by("id")));

			if (listOfAlgo != null) {
				// We geta all of the information about the algorithms from gitlab
				List<Project> listProject = gitlabService.getProjects();

				// We update the information to have fresh information from Gitlab
				setAlgorithmWithGitlabData(listOfAlgo, listProject);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return listOfAlgo;
	}

	@Override
	public Collection<Algorithm> getAlgorithmByCriteria(AlgorithmCriteria algoCriteria) throws BmapException {

		Collection<Algorithm> finalList = new ArrayList<Algorithm>();

		try {
			// First we get all the algorithm from gitlab and also present in the database
			Collection<Algorithm> listOfAlgo = this.getAllAlgorithms();
			Collection<Algorithm> listOfAlgoTopic = new ArrayList<Algorithm>();
			Collection<Algorithm> listOfAlgoTProject = new ArrayList<Algorithm>();

			// We apply the search criteria
			Map<String, List<String>> listOfTopicProject = algoCriteria.getListTopicProject();
			String tags = algoCriteria.getTags();

			// We iterate on the map
			for (Map.Entry<String, List<String>> entry : listOfTopicProject.entrySet()) {
				String topic = entry.getKey();
				// We remove all non desired topic
				for (Algorithm algo : listOfAlgo) {
					if (algo.getTopic() != null && algo.getTopic().equals(topic)) {
						listOfAlgoTopic.add(algo);
					}
				}
			}

			// We iterate to keep only desired projects
			for (Map.Entry<String, List<String>> entry : listOfTopicProject.entrySet()) {
				List<String> projects = entry.getValue();
				String topic = entry.getKey();
				// We remove all non desired project
				if (projects.size() == 0) {
					for (Algorithm algo : listOfAlgoTopic) {
						if (topic.equals(algo.getTopic())) {
							listOfAlgoTProject.add(algo);
						}
					}
				} else {
					for (Algorithm algo : listOfAlgoTopic) {
						if (projects.contains(algo.getProject())) {
							listOfAlgoTProject.add(algo);
						}
					}
				}
			}

			// We filter when have tags in the input
			if (algoCriteria.getTags() != null) {

				if (listOfAlgoTProject.size() != 0) {
					for (Algorithm algo : listOfAlgoTProject) {
						if (algo.getAuthor().getName().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getTags() != null
								&& algo.getTags().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getName().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getDescription() != null
								&& algo.getDescription().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getDocumentation().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getApplicationZone() != null
								&& algo.getApplicationZone().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						}
					}

				} else {
					for (Algorithm algo : listOfAlgo) {

						if (algo.getAuthor().getName().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getTags() != null
								&& algo.getTags().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getName().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getDescription() != null
								&& algo.getDescription().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getDocumentation().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						} else if (algo.getApplicationZone() != null
								&& algo.getApplicationZone().toLowerCase().contains(tags.toLowerCase())) {
							finalList.add(algo);
						}
					}
				}
			} else {
				finalList = listOfAlgoTProject;
			}

			// We filter by the name of the user or tags
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			if (e.getMessage().equals(ExceptionErrorType.NO_SUCH_ELEMENT_EXECEPTION)) {
				throw new BmapException(e.getMessage(), HttpStatus.NOT_FOUND);
			} else {
				throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return finalList;
	}
}
