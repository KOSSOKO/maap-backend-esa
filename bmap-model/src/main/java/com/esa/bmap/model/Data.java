package com.esa.bmap.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * Data
 * 
 * @author QFAURE
 *
 */
@Entity
@Table(name = "data")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({ @JsonSubTypes.Type(value = Granule.class) })
public class Data {
	/**
	 * geometry Type for georeferenced/geolocated data
	 */
	private static final String GEOMETRY_TYPE_GEOLOCATED = "geolocated";

	/**
	 * geometry Type for non georeferenced/geolocated data
	 */
	private static final String GEOMETRY_TYPE_NON_GEOLOCATED = "non-geolocated";

	/**
	 * User data set
	 */
	public static final String USER_DATA_SET = "User ESA MAAP Data Product";

	/**
	 * Unique number identifying the data.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id = null;

	/**
	 * Size of the data file in KB.
	 */
	@Column(name = "fileSize", nullable = false)
	private Float fileSize = null;

	/**
	 * Name of the data file.
	 */
	@Column(name = "fileName", nullable = false)
	private String fileName = null;

	/**
	 * Path to the data file available on the local file system.
	 */
	@Column(name = "filePath", nullable = false)
	private String filePath = null;

	/**
	 * URL http to retrieve the data localy.
	 */
	@Column(name = "urlToData", nullable = false)
	private String urlToData = null;

	/**
	 * Geometry Type of the data (e.g. georeferenced) .
	 */
	@Column(name = "geometryType", nullable = false)
	private String geometryType = null;

	/**
	 * Format of the data (e.g. rg) .
	 */
	@Column(name = "dataFormat", nullable = false)
	private String dataFormat = null;

	/**
	 * Acquisition date and time of the data (e.g. 2007-12-03T10:15:30).
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "acquisition_date", nullable = false)
	private LocalDateTime acquisitionDate = null;

	/**
	 * Upload date and time of the data (e.g. 2007-12-03T10:15:30).
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "upload_date", nullable = false)
	private LocalDateTime uploadDate = null;

	/**
	 * Source data used to generate the data.
	 */
	@OneToOne
	@JoinColumn(name = "source_data_id", nullable = true)
	private Data sourceData;

	/**
	 * MAAP data product status
	 */
	@Column(name = "dataSet", nullable = true)
	private String dataSet = null;

	/**
	 * Collection of algorithms used to generate the data.
	 */
	@ManyToMany
	@JoinTable(name = "data_algorithm", joinColumns = @JoinColumn(name = "data_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "algorithm_id", nullable = false))
	private Collection<Algorithm> collectionAlgorithm;

	/**
	 * Geoserver layer name of the data.
	 */
	@Column(name = "layerName", nullable = true)
	private String layerName = null;

	/**
	 * wmsCartoServerUrl of the data
	 */
	@Column(name = "wmsCartoServerUrl", nullable = true)
	private String wmsCartoServerUrl = null;

	/**
	 * List of the min stored in a string for a tiff with several layers
	 */
	@ElementCollection
	private List<Double> mins = null;

	/**
	 * List of the max stored in a string for a tiff with several layers
	 */
	@ElementCollection
	private List<Double> maxs = null;

	/**
	 * List of the averages stored in a string for a tiff with several layers
	 */
	@ElementCollection
	private List<Double> avgs = null;

	/**
	 * List of the standard deviations stored in a string for a tiff with several
	 * layers
	 */
	@ElementCollection
	private List<Double> stdDeviations = null;

	// @ManyToOne
	// private Granule granule;

	private static final Logger LOG = LoggerFactory.getLogger(Data.class);

	/**
	 * Creates an empty data.
	 */
	public Data() {
		super();
	}

	/**
	 * Creates a data with the specified parameters.
	 * 
	 * @param fileSize Size of the data file in KB.
	 * @param fileName Name of the data file.
	 * @param filePath Path to the data file.
	 * @param acquisitionDate Acquisition date and time of the data (e.g.
	 *            2007-12-03T10:15:30).
	 * @param uploadDate Upload date and time of the data (e.g.
	 *            2007-12-03T10:15:30).
	 * @param sourceData Source data used to generate the data.
	 * @param collectionAlgorithm Collection of algorithms used to generate the
	 *            data.
	 * @param layerName Geoserver layer name of the data.
	 * @param dataFormat Format of the data (e.g: rg)
	 * @param dataSet MAAP data product status (e.g “Official MAAP Data Product”)
	 * @throws BmapException When one or more of the mandatory parameters are null.
	 */
	public Data(Float fileSize, String fileName, String filePath, LocalDateTime acquisitionDate,
			LocalDateTime uploadDate, Data sourceData, Collection<Algorithm> collectionAlgorithm, String layerName,
			String geometryType, String dataFormat, String dataSet, String urlToData,
			Map<String, List<Double>> mapStats) throws BmapException {
		super();
		if (fileSize != null && fileName != null && filePath != null && acquisitionDate != null && uploadDate != null) {
			this.fileSize = fileSize;
			this.fileName = fileName;
			this.filePath = filePath;
			this.acquisitionDate = acquisitionDate;
			this.uploadDate = uploadDate;
			this.sourceData = sourceData;
			this.collectionAlgorithm = collectionAlgorithm;
			this.layerName = layerName;
			this.geometryType = geometryType;
			this.dataFormat = dataFormat;
			this.dataSet = dataSet;
			this.urlToData = urlToData;
			if (mapStats != null) {
				this.mins = mapStats.get("listMin");
				this.maxs = mapStats.get("listMax");
				this.avgs = mapStats.get("listAvg");
				this.stdDeviations = mapStats.get("listStdDev");
			}

		} else {
			LOG.error(Common.getMessageValue("data.constructor.error"));
			throw new BmapException(Common.getMessageValue("data.constructor.error"));
		}
	}

	/**
	 * @return The id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return The fileSize.
	 */
	public Float getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize The fileSize to set.
	 */
	public void setFileSize(Float fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * @return The fileName.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the dataFormat
	 */
	public String getDataFormat() {
		return dataFormat;
	}

	/**
	 * @param dataFormat the dataFormat to set
	 */
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}

	/**
	 * @return the dataSet
	 */
	public String getDataSet() {
		return dataSet;
	}

	/**
	 * @param dataSet the dataSet to set
	 */
	public void setDataSet(String dataSet) {
		this.dataSet = dataSet;
	}

	/**
	 * @param fileName The fileName to set.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return The filePath.
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath The filePath to set.
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return The acquisitionDate.
	 */
	public LocalDateTime getAcquisitionDate() {
		return acquisitionDate;
	}

	/**
	 * @param acquisitionDate The acquisitionDate to set.
	 */
	public void setAcquisitionDate(LocalDateTime acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	/**
	 * @return The uploadDate.
	 */
	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	/**
	 * @param uploadDate The uploadDate to set.
	 */
	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	/**
	 * @return The sourceData.
	 */
	public Data getSourceData() {
		return sourceData;
	}

	/**
	 * @param sourceData The sourceData to set.
	 */
	public void setSourceData(Data sourceData) {
		this.sourceData = sourceData;
	}

	/**
	 * @return The collectionAlgorithm.
	 */
	public Collection<Algorithm> getCollectionAlgorithm() {
		return collectionAlgorithm;
	}

	/**
	 * @param collectionAlgorithm The collectionAlgorithm to set.
	 */
	public void setCollectionAlgorithm(Collection<Algorithm> collectionAlgorithm) {
		this.collectionAlgorithm = collectionAlgorithm;
	}

	/**
	 * @return The layerName.
	 */
	public String getLayerName() {
		return layerName;
	}

	/**
	 * @param layerName The layerName to set.
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}

	/**
	 * @return the urlToData
	 */
	public String getUrlToData() {
		return urlToData;
	}

	/**
	 * @param urlToData the urlToData to set
	 */
	public void setUrlToData(String urlToData) {
		this.urlToData = urlToData;
	}

	/**
	 * @return the geometryType
	 */
	public String getGeometryType() {
		return geometryType;
	}

	/**
	 * @param geometryType the geometryType to set
	 */
	public void setGeometryType(String geometryType) {
		this.geometryType = geometryType;
	}

	/**
	 * @return the mins
	 */
	public List<Double> getMins() {
		return mins;
	}

	/**
	 * @param mins the mins to set
	 */
	public void setMins(List<Double> mins) {
		this.mins = mins;
	}

	/**
	 * @return the maxs
	 */
	public List<Double> getMaxs() {
		return maxs;
	}

	/**
	 * @param maxs the maxs to set
	 */
	public void setMaxs(List<Double> maxs) {
		this.maxs = maxs;
	}

	/**
	 * @return the avgs
	 */
	public List<Double> getAvgs() {
		return avgs;
	}

	/**
	 * @param avgs the avgs to set
	 */
	public void setAvgs(List<Double> avgs) {
		this.avgs = avgs;
	}

	/**
	 * @return the stdDeviations
	 */
	public List<Double> getStdDeviations() {
		return stdDeviations;
	}

	/**
	 * @param stdDeviations the stdDeviations to set
	 */
	public void setStdDeviations(List<Double> stdDeviations) {
		this.stdDeviations = stdDeviations;
	}

	/**
	 * Adds the specified algorithm to the collection.
	 * 
	 * @param algorithm the algorithm to add to the collection.
	 */
	public void addAlgorithmToList(Algorithm algorithm) {
		if (this.collectionAlgorithm == null) {
			this.collectionAlgorithm = new ArrayList<>();
		}
		this.collectionAlgorithm.add(algorithm);
	}

	/**
	 * @return the wmsCartoServerUrl
	 */
	public String getWmsCartoServerUrl() {
		return wmsCartoServerUrl;
	}

	/**
	 * @param wmsCartoServerUrl the wmsCartoServerUrl to set
	 */
	public void setWmsCartoServerUrl(String wmsCartoServerUrl) {
		this.wmsCartoServerUrl = wmsCartoServerUrl;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Data data = (Data) o;
		return Objects.equals(this.id, data.id) && Objects.equals(this.fileSize, data.fileSize)
				&& Objects.equals(this.fileName, data.fileName) && Objects.equals(this.filePath, data.filePath)
				&& Objects.equals(this.urlToData, data.urlToData)
				&& Objects.equals(this.geometryType, data.geometryType)
				&& Objects.equals(this.dataFormat, data.dataFormat)
				&& Objects.equals(this.acquisitionDate, data.acquisitionDate)
				&& Objects.equals(this.uploadDate, data.uploadDate) && Objects.equals(this.sourceData, data.sourceData)
				&& Objects.equals(this.dataSet, data.dataSet)
				&& Objects.equals(this.collectionAlgorithm, data.collectionAlgorithm)
				&& Objects.equals(this.layerName, data.layerName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.fileSize, this.fileName, this.filePath, this.urlToData, this.geometryType,
				this.dataFormat, this.dataSet, this.acquisitionDate, this.uploadDate, this.sourceData,
				this.collectionAlgorithm, this.layerName);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Data {\n");
		sb.append("    id: ").append(Utils.toIndentedString(this.id)).append("\n");
		sb.append("    fileSize: ").append(Utils.toIndentedString(this.fileSize)).append("\n");
		sb.append("    fileName: ").append(Utils.toIndentedString(this.fileName)).append("\n");
		sb.append("    filePath: ").append(Utils.toIndentedString(this.filePath)).append("\n");
		sb.append("    urlToData: ").append(Utils.toIndentedString(this.urlToData)).append("\n");
		sb.append("    geometryType: ").append(Utils.toIndentedString(this.geometryType)).append("\n");
		sb.append("    dataFormat: ").append(Utils.toIndentedString(this.dataFormat)).append("\n");
		sb.append("    dataSet: ").append(Utils.toIndentedString(this.dataSet)).append("\n");
		sb.append("    acquisitionDate: ").append(Utils.toIndentedString(this.acquisitionDate)).append("\n");
		sb.append("    uploadDate: ").append(Utils.toIndentedString(this.uploadDate)).append("\n");
		sb.append("    sourceData: ").append(Utils.toIndentedString(this.sourceData)).append("\n");
		sb.append("    listAlgorithm: ").append(Utils.toIndentedString(this.collectionAlgorithm)).append("\n");
		sb.append("    layerName: ").append(Utils.toIndentedString(this.layerName)).append("\n");
		sb.append("    minsValues: ").append(Utils.toIndentedString(this.mins)).append("\n");
		sb.append("    maxValues: ").append(Utils.toIndentedString(this.maxs)).append("\n");
		sb.append("    avgValues: ").append(Utils.toIndentedString(this.avgs)).append("\n");
		sb.append("    stdDeviationsValues: ").append(Utils.toIndentedString(this.stdDeviations)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
