package com.esa.bmap.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Algorithm
 */
@Entity
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Algorithm {
	
	//Unique identifier representing a specific Algorithm.
	@Id
   	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id = null;
	
	//Name of the Algorithm.
	@Column(unique = true, nullable = false)
	private String name = null;

	//The current version of the algorithm.
	@Column(name = "current_version", unique = false, nullable = true)
	private String currentVersion;
	
	//Specify what king of coordinate work with an algorithm.
	@Column(name = "application_zone",unique = false, nullable = true)
	private String applicationZone = null;
	
	//Indicate the average time of execution of an algorithm in seconds.
	@Column(name = "average_time", unique = false, nullable = true)
	private String averageTime = null;
	
	// status of the algorithm
	//@JsonProperty("status")
   	@Column(unique = false, nullable = false)
   	@Enumerated(EnumType.STRING)
	private Status status = null;
	
	//Privacy of the algorithm
   	@Column(unique = false, nullable = false)
   	@Enumerated(EnumType.STRING)
	private Privacy privacy = null;
	
	//Executable of this algorithm
   	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "id_executable", nullable = true)
	private Executable executable = null;
	
	//Specify what king of coordinate work with an algorithm.
	@Column(name = "description",unique = false, nullable = true)
	private String description = null;

	//Name of the author of the algorithm
    @ManyToOne
    @JoinColumn(name = "id_user")
	private BmaapUser author = null;

	//Url source code of the algorithm, configuration file and documentation. 
	@Column(name = "git_url_source_code", unique = true, nullable = true)
	private String gitUrlSource = null;
	
	//Url of the algorithm on gitlab, configuration file and documentation. 
	@Column(name = "git_url", unique = true, nullable = true)
	private String gitUrl = null;

	//Documentation of the algorithm 
	@Column(unique = false, nullable = true)
	private String documentation = null;
	
	//Configuration of the algorithm 
	@Column(unique = false, nullable = true)
	private String configuration = null;
	
	//The topic attached to this algorithm
	@Column(name = "topic", unique = false, nullable = true)
	private String topic = null;

	//The project attached to this algorithm
	@Column(name = "project", unique = false, nullable = true)
	private String project = null;
	
	//The last update date
	@Column(name = "last_update_date", unique = false, nullable = true)
	private String lastUpdateDate = null;
	
	
	//The tags attached to this algorithm
	@Column(name = "tags", unique = false, nullable = true)
	private String tags = null;
	/**
	 * Default constructor
	 */
	public Algorithm() {
		super();
	}
	
	/**
	 * algorithm constructor
	 * @param algorithmName
	 * @param applicationZone
	 * @param averageTime
	 * @param gitUrl
	 * @param currentVer
	 * @param documentation
	 * @param icon
	 * @param privacy
	 * @param status
	 */
	public Algorithm(String name, BmaapUser author, String applicationZone, String averageTime, String gitUrl, String gitUrlSource,
			String currentVersion, String documentation, Privacy privacy, Status status, String topic, String project, String tags, String lastUpdateDate) {
		super();
		this.name = name;
		this.author = author;
		this.applicationZone = applicationZone;
		this.averageTime = averageTime;
		this.gitUrl = gitUrl;
		this.gitUrlSource = gitUrlSource;
		this.currentVersion = currentVersion;
		this.documentation = documentation;
		this.privacy = privacy;
		this.status = status;
		this.topic = topic;
		this.project = project;
		this.tags = tags;
		this.lastUpdateDate = lastUpdateDate;
	}
	





	/**
	 * get Unique identifier representing a specific Algorithm.
	 * @return algorithmId
	 **/
	public Integer getId() {
		return id;
	}

	/**
	 * @return the currentVersion
	 */
	public String getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * @param currentVersion the currentVersion to set
	 */
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the author
	 */
	public BmaapUser getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(BmaapUser author) {
		this.author = author;
	}

	/**
	 * @return the gitUrl
	 */
	public String getGitUrl() {
		return gitUrl;
	}

	/**
	 * @param gitUrl the gitUrl to set
	 */
	public void setGitUrl(String gitUrl) {
		this.gitUrl = gitUrl;
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

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * set Unique identifier representing a specific Algorithm.
	 * @param algorithmId
	 */
	public void setAlgorithmId(Integer algorithmId) {
		this.id = algorithmId;
	}

	/**
	 * name of the Algorithm.
	 * @param algorithmName
	 * @return
	 */
	public Algorithm algorithmName(String algorithmName) {
		this.name = algorithmName;
		return this;
	}

	/**
	 * get name of the Algorithm.
	 * @return algorithmName
	 **/
	public String getName() {
		return name;
	}

	
	/**
	 * set name of the Algorithm.
	 * @param algorithmName
	 */
	public void setName(String algorithmName) {
		this.name = algorithmName;
	}

	/**
	 * specify what king of coordinate work with an algorithm.
	 * @param applicationZone
	 * @return
	 */
	public Algorithm applicationZone(String applicationZone) {
		this.applicationZone = applicationZone;
		return this;
	}

	/**
	 * get specify what king of coordinate work with an algorithm.
	 * @return applicationZone
	 **/
	public String getApplicationZone() {
		return applicationZone;
	}

	/**
	 * set specify what king of coordinate work with an algorithm.
	 * @param applicationZone
	 */
	public void setApplicationZone(String applicationZone) {
		this.applicationZone = applicationZone;
	}

	/**
	 * indicate the average time of execution of an algorithm.
	 * @param averageTime
	 * @return
	 */
	public Algorithm averageTime(String averageTime) {
		this.averageTime = averageTime;
		return this;
	}

	/**
	 *  get indicate the average time of execution of an algorithm.
	 * @return averageTime
	 **/
	public String getAverageTime() {
		return averageTime;
	}
	/**
	 * set indicate the average time of execution of an algorithm.
	 * @param averageTime
	 */
	public void setAverageTime(String averageTime) {
		this.averageTime = averageTime;
	}


	/**
	 * documentation of the algorithm and how we can use identifier. it's an url
	 * @param documentation
	 * @return
	 */
	public Algorithm documentation(String documentation) {
		this.documentation = documentation;
		return this;
	}

	/**
	 * get documentation of the algorithm and how we can use identifier. it's an url
	 * @return documentation
	 **/
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * set documentation of the algorithm and how we can use identifier. it's an url
	 * @param documentation
	 */
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

	/**
	 * @return the configuration
	 */
	public String getConfiguration() {
		return configuration;
	}

	/**
	 * @param configuration the configuration to set
	 */
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	/**
	 * 
	 * 
	 * @param executable
	 * @return
	 */
	public Algorithm executable(Executable executable) {
		this.executable = executable;
		return this;
	}

	/**
	 * Get executable
	 * @return executable
	 **/
	public Executable getExecutable() {
		return executable;
	}

	/**
	 * set Executable
	 * @param executable
	 */
	public void setExecutable(Executable executable) {
		this.executable = executable;
	}

	/**
	 * privacy of the algorithm
	 * @param privacy
	 * @return
	 */
	public Algorithm privacy(Privacy privacy) {
		this.privacy = privacy;
		return this;
	}

	/**
	 * Get privacy
	 * @return privacy
	 **/
	public Privacy getPrivacy() {
		return privacy;
	}

	/**
	 * set privacy of the algorithm
	 * @param privacy
	 */
	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	/**
	 * Get project
	 * @return String
	 **/
	public String getProject() {
		return project;
	}

	/**
	 * set the project where the algorithm belong
	 * @param void
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * Get Topic
	 * @return String
	 **/
	public String getTopic() {
		return topic;
	}

	/**
	 * set the topic where the algorithm belong
	 * @param void
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}
	/**
	 * Get status
	 * @return status
	 **/
	public Status getStatus() {
		return status;
	}

	/**
	 * Set status
	 * @param status
	 */
	public void setStatusEnum(Status status) {
		this.status = status;
	}

	/**
	 * @return the gitUrlSource
	 */
	public String getGitUrlSource() {
		return gitUrlSource;
	}

	/**
	 * @param gitUrlSource the gitUrlSource to set
	 */
	public void setGitUrlSource(String gitUrlSource) {
		this.gitUrlSource = gitUrlSource;
	}

	/**
	 * @return the lastUpdateDate
	 */
	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * @param lastUpdateDate the lastUpdateDate to set
	 */
	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	/**
	 * equals to compare two objects
	 */
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Algorithm algorithm = (Algorithm) o;
		return Objects.equals(this.id, algorithm.id) &&
				Objects.equals(this.name, algorithm.name) &&
				Objects.equals(this.author, algorithm.author) &&
				Objects.equals(this.applicationZone, algorithm.applicationZone) &&
				Objects.equals(this.averageTime, algorithm.averageTime) &&
				Objects.equals(this.gitUrl, algorithm.gitUrl) &&
				Objects.equals(this.gitUrlSource, algorithm.gitUrlSource) &&
				Objects.equals(this.currentVersion, algorithm.currentVersion) &&
				Objects.equals(this.documentation, algorithm.documentation) &&
				Objects.equals(this.executable, algorithm.executable) &&
				Objects.equals(this.privacy, algorithm.privacy) &&
				Objects.equals(this.project, algorithm.project) &&
				Objects.equals(this.status, algorithm.status) &&
				Objects.equals(this.lastUpdateDate, algorithm.lastUpdateDate) &&
				Objects.equals(this.tags, algorithm.tags);
	}

	/**
	 * Override hascode
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.author, this.applicationZone, this.averageTime, this.currentVersion, this.documentation, this.executable,
				 this.privacy, this.project, this.status, super.hashCode());
	}
	
	/**
	 * Convert algorithm to string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Algorithm {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    author: ").append(toIndentedString(author)).append("\n");
		sb.append("    applicationZone: ").append(toIndentedString(applicationZone)).append("\n");
		sb.append("    averageTime: ").append(toIndentedString(averageTime)).append("\n");
		sb.append("    gitUrl: ").append(toIndentedString(gitUrl)).append("\n");
		sb.append("    gitUrlSource: ").append(toIndentedString(gitUrlSource)).append("\n");
		sb.append("    currentVersion: ").append(toIndentedString(currentVersion)).append("\n");
		sb.append("    documentation: ").append(toIndentedString(documentation)).append("\n");
		sb.append("    executable: ").append(toIndentedString(executable)).append("\n");
		sb.append("    privacy: ").append(toIndentedString(privacy)).append("\n");
		sb.append("    topic: ").append(toIndentedString(topic)).append("\n");
		sb.append("    project: ").append(toIndentedString(project)).append("\n");
		sb.append("    statusEnum: ").append(toIndentedString(status)).append("\n");
		sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
		sb.append("    lastUpdateDate: ").append(toIndentedString(lastUpdateDate)).append("\n");
		sb.append("}");
		return sb.toString();
	}
	

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}





}

