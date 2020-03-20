package com.esa.bmap.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.esa.bmap.common.exceptions.BmapException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

/**
 * This class represents a granule.
 * 
 * @author QFAURE
 *
 */
@Entity
@Table(name = "granule")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Granule {

	public static final String GRANULE_ID_DELIMITER = ":@";
	public static final String RASTER_EXTENSION_TIFF = "tiff";
	public static final String RASTER_EXTENSION_TIF = "tif";

	// The constant for the product type
	public static final String PRODUCT_TYPE_SLC = "SLC";
	public static final String PRODUCT_TYPE_DEM = "DEM";
	public static final String PRODUCT_TYPE_GRD = "GRD";
	public static final String PRODUCT_TYPE_ROI = "ROI";
	public static final String PRODUCT_TYPE_INC = "INC";
	public static final String PRODUCT_TYPE_AZ = "AZ";
	public static final String PRODUCT_TYPE_RG = "RG";
	public static final String PRODUCT_TYPE_KZ = "KZ";
	
	
	// The constants for the geometryType
	public static final String GEOMETRY_TYPE_GEOLOCATED = "geolocated";
	public static final String GEOMETRY_TYPE_NON_GEOLOCATED = "non-geolocated";
	/**
	 * extension for azimuth LUT table
	 */
	public static final String EXTENSION_AZ = "az.tiff";
	/**
	 * extension for range LUT table
	 */
	public static final String EXTENSION_RG = "rg.tiff";
	/**
	 * extension for dem
	 */
	public static final String EXTENSION_DEM = "dem.tiff";

	/**
	 * extension for incidence angles
	 */
	public static final String EXTENSION_INC = "inc.tiff";

	/**
	 * extension for kz
	 */
	public static final String EXTENSION_KZ = "kz.tiff";

	/**
	 * extension for ROI (shp)
	 */
	public static final String EXTENSION_SHP = ".shp";

	/**
	 * extension for ROI (shx)
	 */
	public static final String EXTENSION_SHX = ".shx";

	/**
	 * extension for ROI (prj)
	 */
	public static final String EXTENSION_PRJ = ".prj";
	/**
	 * extension for ROI (dbf)
	 */
	public static final String EXTENSION_DBF = ".dbf";

	/**
	 * extension for ROI (shp)
	 */
	public static final String PRODUCT_TYPE_SHP = "shp";

	/**
	 * extension for ROI (shx)
	 */
	public static final String PRODUCT_TYPE_SHX = "shx";

	/**
	 * extension for ROI (prj)
	 */
	public static final String PRODUCT_TYPE_PRJ = "prj";
	/**
	 * extension for ROI (dbf)
	 */
	public static final String PRODUCT_TYPE_DBF = "dbf";
	/**
	 * extension zip files
	 */
	public static final String EXTENSION_ZIP = ".zip";
	/**
	 * EPSG CODE 4326
	 */
	public static final String EPSG_CODE_4326 = "EPSG:4326";
	/**
	 * EPSG CODE 404000
	 */
	public static final String EPSG_CODE_404000 = "EPSG:404000";

	/**
	 * Unique number identifying the granule.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	/**
	 * name of the Granule
	 */
	@Column(name = "name")
	private String name = null;

	/**
	 * Direction of flight, clockwise, with respect to the North (deg)
	 */
	@Column(name = "heading", nullable = true)
	private String heading = null;

	/**
	 * z_flight: Average altitude of flight (m)
	 */
	@Column(name = "zFlight", nullable = true)
	private String zFlight = null;

	/**
	 * z_terrain: Average altitude of the ground (m)
	 */
	@Column(name = "zTerrain", nullable = true)
	private String zTerrain = null;

	/**
	 * SLR_start: Slant range swath start (m)
	 */
	@Column(name = "slrStart", nullable = true)
	private String slrStart = null;

	/**
	 * pixel_spacing: Slant range pixel spacing (m)
	 */
	@Column(name = "pixelSpacing", nullable = true)
	private String pixelSpacing = null;

	/**
	 * surface_resol: A0 used for sigma0 computation (m2)
	 */
	@Column(name = "surfaceResol", nullable = true)
	private String surfaceResol = null;

	/**
	 * GRD_resol: Ground range pixel spacing (m)
	 */
	@Column(name = "grdResol", nullable = true)
	private String grdResol = null;

	/**
	 * The last update of the Granule
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "updateDate", nullable = false)
	private LocalDateTime updateDate = null;

	/**
	 * dem: Suffixe of the dem map corresponding to the image
	 */
	@Column(name = "dem", nullable = true)
	private String dem = null;

	/**
	 * master: Name of master image if the image is coregistrated
	 */
	@Column(name = "master", nullable = true)
	private String master = null;

	/**
	 * Product type (e.g. SLC, DEM).
	 */
	@Column(name = "product_type", nullable = true)
	private String productType = null;

	/**
	 * Native Spatial reference system code
	 */
	@Column(name = "epsgCodeNative", nullable = true)
	private String epsgCodeNative = null;

	/**
	 * Declared Spatial reference system code
	 */
	@Column(name = "epsgCodeDeclared", nullable = true)
	private String epsgCodeDeclared = null;

	/**
	 * Spatial reference system code
	 */
	@Column(name = "orbit_direction", nullable = true)
	@Enumerated(EnumType.STRING)
	private OrbitDirection orbitDirection = null;

	/**
	 * Spatial reference system code
	 */
	@Column(name = "orbit_number", nullable = true)
	private Integer orbitNumber = null;

	/**
	 * Width of the matrix if raster type
	 */
	@Column(name = "width", nullable = true)
	private Double width = null;

	/**
	 * height of the matrix if raster type
	 */
	@Column(name = "height", nullable = true)
	private Double height = null;

	/**
	 * Scene granule the data granule is attached to.
	 */
	@OneToOne
	@JoinColumn(name = "granule_scene_id", nullable = true)
	private Granule granuleScene;

	/**
	 * Platform of the granule dataList.
	 */
	@ManyToOne
	@JoinColumn(name = "platform_id")
	private Platform platform = null;

	/**
	 * Bounding box of the airborne dataList.
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "quadrangle_id", nullable = true)
	private Quadrangle quadrangle = null;

	/**
	 * Polarization of the airborne dataList (e.g. HH, HV).
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "polarization", nullable = true, length = 2)
	private Polarization polarization = null;

	/**
	 * Sub-region of the airborne dataList.
	 */
	@ManyToOne
	@JoinColumn(name = "collection_id")
	private Collection collection = null;
	/**
	 * Sub-region of the airborne dataList.
	 */
	@ManyToOne
	@JoinColumn(name = "sub_region_id")
	private SubRegion subRegion = null;

	/**
	 * List of dataList associated to this granule
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Data> dataList = null;

	/**
	 * List of granules associated to this granule if this granule is a scene
	 * 
	 */
	@ElementCollection
	private List<String> granuleList = null;

	/**
	 * userId of the data if the data was generated by a user layers
	 */
	// Name of the author of the algorithm
	@ManyToOne
	@JoinColumn(name = "id_user")
	private BmaapUser author = null;

	// status of the granule
	@Column(unique = false, nullable = false, length = 32, columnDefinition = "varchar(32) default 'SHARED'")
	@Enumerated(EnumType.STRING)
	private Status status = null;

	// Privacy of the granule
	@Column(unique = false, nullable = false, length = 32, columnDefinition = "varchar(32) default 'PUBLIC'")
	@Enumerated(EnumType.STRING)
	private Privacy privacy = null;

	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String granuleUr = null;

	/**
	 * Creates an empty granule.
	 */
	public Granule() {
		super();
	}

	/**
	 * Method creating a granule corresponding to a scene granule with the specified
	 * parameters
	 * 
	 * @param name
	 * @param heading
	 * @param zFlight
	 * @param zTerrain
	 * @param slrStart
	 * @param pixelSpacing
	 * @param surfaceResol
	 * @param grdResol
	 * @param updateDate
	 * @param dem
	 * @param master
	 * @param platform
	 * @param quadrangle
	 * @param collection
	 * @param subRegion
	 * @param granuleList
	 */
	public Granule(String name, String heading, String zFlight, String zTerrain, String slrStart, String pixelSpacing,
			String surfaceResol, String grdResol, LocalDateTime updateDate, String dem, String master,
			Platform platform, Quadrangle quadrangle, Collection collection, SubRegion subRegion,
			List<String> granuleList) {
		super();
		this.name = name;
		this.heading = heading;
		this.zFlight = zFlight;
		this.zTerrain = zTerrain;
		this.slrStart = slrStart;
		this.pixelSpacing = pixelSpacing;
		this.surfaceResol = surfaceResol;
		this.grdResol = grdResol;
		this.updateDate = updateDate;
		this.dem = dem;
		this.master = master;
		this.platform = platform;
		this.collection = collection;
		this.subRegion = subRegion;
		this.granuleList = granuleList;
	}

	/**
	 * Method creating a granule
	 * 
	 * @param name
	 * @param updateDate
	 * @param dem
	 * @param master
	 * @param productType
	 * @param epsgCodeNative
	 * @param epsgCodeDeclared
	 * @param width
	 * @param height
	 * @param platform
	 * @param quadrangle
	 * @param polarization
	 * @param collection
	 * @param subRegion
	 * @param status
	 * @param privacy
	 * @throws BmapException
	 */
	public Granule(String name, LocalDateTime updateDate, String dem, String master, String productType,
			String epsgCodeNative, String epsgCodeDeclared, Double width, Double height, Platform platform,
			Quadrangle quadrangle, Polarization polarization, Collection collection, SubRegion subRegion, Status status,
			Privacy privacy) throws BmapException {
		super();
		this.dataList = new ArrayList<Data>();
		this.status = status;
		this.privacy = privacy;
		this.name = name;
		this.updateDate = updateDate;
		this.dem = dem;
		this.master = master;
		this.productType = productType;
		this.epsgCodeNative = epsgCodeNative;
		this.epsgCodeDeclared = epsgCodeDeclared;
		this.collection = collection;
		this.subRegion = subRegion;
		this.platform = platform;
		this.quadrangle = quadrangle;
		this.polarization = polarization;
		this.width = width;
		this.height = height;
	}

	/**
	 * Creates an granule and creates a single Data attached to this granule with
	 * the given parameters
	 * 
	 * @param name
	 * @param updateDate
	 * @param dem
	 * @param master
	 * @param productType
	 * @param epsgCodeNative
	 * @param epsgCodeDeclared
	 * @param width
	 * @param height
	 * @param platform
	 * @param quadrangle
	 * @param polarization
	 * @param collection
	 * @param subRegion
	 * @param fileSize
	 * @param filePath
	 * @param acquisitionDate
	 * @param uploadDate
	 * @param sourceData
	 * @param collectionAlgorithm
	 * @param layerName
	 * @param geometryType
	 * @param dataFormat
	 * @param granuleScene
	 * @param urlToData
	 * @param mapStats
	 * @throws BmapException
	 */
	public Granule(String name, LocalDateTime updateDate, String dem, String master, String productType,
			String epsgCodeNative, String epsgCodeDeclared, Double width, Double height, Platform platform,
			Quadrangle quadrangle, Polarization polarization, Collection collection, SubRegion subRegion,
			Float fileSize, String filePath, LocalDateTime acquisitionDate, LocalDateTime uploadDate, Data sourceData,
			java.util.Collection<Algorithm> collectionAlgorithm, String layerName, String geometryType,
			String dataFormat, Granule granuleScene, String urlToData, Map<String, List<Double>> mapStats)
			throws BmapException {
		super();

		this.dataList = new ArrayList<Data>();
		createData(fileSize, name, filePath, acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName,
				geometryType, dataFormat, urlToData, mapStats);

		this.name = name;
		this.updateDate = updateDate;
		this.dem = dem;
		this.status = granuleScene.status;
		this.privacy = granuleScene.privacy;
		this.master = master;
		this.productType = productType;
		this.epsgCodeNative = epsgCodeNative;
		this.epsgCodeDeclared = epsgCodeDeclared;
		this.collection = collection;
		this.subRegion = subRegion;
		this.platform = platform;
		this.quadrangle = quadrangle;
		this.polarization = polarization;
		this.width = width;
		this.height = height;
		this.granuleScene = granuleScene;
	}

	/**
	 * Granule with just mandatory data Use for example to ingest private data
	 * 
	 * @param name
	 * @param productType
	 * @param width
	 * @param height
	 * @param quadrangle
	 * @param polarization
	 * @param subRegion
	 */
	public Granule(String name, String productType, Double width, Double height, Quadrangle quadrangle,
			Polarization polarization, SubRegion subRegion) {

		super();
		this.name = name;
		this.updateDate = LocalDateTime.now();
		this.productType = productType;
		this.subRegion = subRegion;
		this.quadrangle = quadrangle;
		this.polarization = polarization;
		this.width = width;
		this.height = height;

	}

	/**
	 * Granule with just mandatory data Use for example to ingest private data
	 * 
	 * @param name
	 * @param productType
	 * @param width
	 * @param height
	 * @param quadrangle
	 * @param polarization
	 * @param collectionAlgorithm
	 * @param subRegion
	 */
	public Granule(String name, String productType, Double width, Double height, Quadrangle quadrangle,
			Polarization polarization, java.util.Collection<Algorithm> collectionAlgorithm, SubRegion subRegion) {

		super();
		this.dataList = new ArrayList<Data>();

		this.name = name;
		this.updateDate = LocalDateTime.now();
		this.productType = productType;
		this.subRegion = subRegion;
		this.quadrangle = quadrangle;
		this.polarization = polarization;
		this.width = width;
		this.height = height;

	}

	/**
	 * This method creates a new dataList associated to this granule
	 * 
	 * @param fileSize
	 * @param fileName
	 * @param filePath
	 * @param acquisitionDate
	 * @param uploadDate
	 * @param sourceData
	 * @param collectionAlgorithm
	 * @param layerName
	 * @param geometryType
	 * @param dataFormat
	 * @param urlToData
	 * @param mapStats
	 * @throws BmapException
	 */
	private void createData(Float fileSize, String fileName, String filePath, LocalDateTime acquisitionDate,
			LocalDateTime uploadDate, Data sourceData, java.util.Collection<Algorithm> collectionAlgorithm,
			String layerName, String geometryType, String dataFormat, String urlToData,
			Map<String, List<Double>> mapStats) throws BmapException {
		Data aData = new Data(fileSize, fileName, filePath, acquisitionDate, uploadDate, sourceData,
				collectionAlgorithm, layerName, geometryType, dataFormat, null, urlToData, mapStats);

		dataList.add(aData);
	}

	/**
	 * @return the granuleList
	 */
	public List<String> getGranuleList() {
		if (granuleList == null) {
			granuleList = new ArrayList<String>();
		}
		return granuleList;
	}

	/**
	 * @param granuleList the granuleList to set
	 */
	public void setGranuleList(List<String> granuleList) {
		this.granuleList = granuleList;
	}

	/**
	 * @param Granule name String to add to the list
	 */
	public void addToGranuleList(String granuleName) {
		this.granuleList.add(granuleName);
	}

	/**
	 * @return the granuleScene
	 */
	public Granule getGranuleScene() {
		return granuleScene;
	}

	/**
	 * @param granuleScene the granuleScene to set
	 */
	public void setGranuleScene(Granule granuleScene) {
		this.granuleScene = granuleScene;
	}

	/**
	 * @return The productType.
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType The productType to set.
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return The platform.
	 */
	public Platform getPlatform() {
		return platform;
	}

	/**
	 * @param platform The platform to set.
	 */
	public void setPlatform(Platform platform) {
		this.platform = platform;
	}

	/**
	 * @return The quadrangle.
	 */
	public Quadrangle getQuadrangle() {
		return quadrangle;
	}

	/**
	 * @param quadrangle The quadrangle to set.
	 */
	public void setQuadrangle(Quadrangle quadrangle) {
		this.quadrangle = quadrangle;
	}

	/**
	 * @return The polarization.
	 */
	public Polarization getPolarization() {
		return polarization;
	}

	/**
	 * @param polarization The polarization to set.
	 */
	public void setPolarization(Polarization polarization) {
		this.polarization = polarization;
	}

	/**
	 * @return The subRegion.
	 */
	public SubRegion getSubRegion() {
		return subRegion;
	}

	/**
	 * @return the updateDate
	 */
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the epsgCodeNative
	 */
	public String getEpsgCodeNative() {
		return epsgCodeNative;
	}

	/**
	 * @param epsgCodeNative the epsgCodeNative to set
	 */
	public void setEpsgCodeNative(String epsgCodeNative) {
		this.epsgCodeNative = epsgCodeNative;
	}

	/**
	 * @return the epsgCodeDeclared
	 */
	public String getEpsgCodeDeclared() {
		return epsgCodeDeclared;
	}

	/**
	 * @param epsgCodeDeclared the epsgCodeDeclared to set
	 */
	public void setEpsgCodeDeclared(String epsgCodeDeclared) {
		this.epsgCodeDeclared = epsgCodeDeclared;
	}

	/**
	 * @return the dataList
	 */
	public List<Data> getDataList() {
		if (dataList == null) {
			dataList = new ArrayList<Data>();
		}
		return dataList;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<Data> dataList) {
		this.dataList = dataList;
	}

	/**
	 * @param subRegion The subRegion to set.
	 */
	public void setSubRegion(SubRegion subRegion) {
		this.subRegion = subRegion;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the heading
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * @param heading the heading to set
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}

	/**
	 * @return the zFlight
	 */
	public String getzFlight() {
		return zFlight;
	}

	/**
	 * @param zFlight the zFlight to set
	 */
	public void setzFlight(String zFlight) {
		this.zFlight = zFlight;
	}

	/**
	 * @return the zTerrain
	 */
	public String getzTerrain() {
		return zTerrain;
	}

	/**
	 * @param zTerrain the zTerrain to set
	 */
	public void setzTerrain(String zTerrain) {
		this.zTerrain = zTerrain;
	}

	/**
	 * @return the slrStart
	 */
	public String getSlrStart() {
		return slrStart;
	}

	/**
	 * @param slrStart the slrStart to set
	 */
	public void setSlrStart(String slrStart) {
		this.slrStart = slrStart;
	}

	/**
	 * @return the pixelSpacing
	 */
	public String getPixelSpacing() {
		return pixelSpacing;
	}

	/**
	 * @param pixelSpacing the pixelSpacing to set
	 */
	public void setPixelSpacing(String pixelSpacing) {
		this.pixelSpacing = pixelSpacing;
	}

	/**
	 * @return the surfaceResol
	 */
	public String getSurfaceResol() {
		return surfaceResol;
	}

	/**
	 * @param surfaceResol the surfaceResol to set
	 */
	public void setSurfaceResol(String surfaceResol) {
		this.surfaceResol = surfaceResol;
	}

	/**
	 * @return the grdResol
	 */
	public String getGrdResol() {
		return grdResol;
	}

	/**
	 * @param grdResol the grdResol to set
	 */
	public void setGrdResol(String grdResol) {
		this.grdResol = grdResol;
	}

	/**
	 * @return the dem
	 */
	public String getDem() {
		return dem;
	}

	/**
	 * @param dem the dem to set
	 */
	public void setDem(String dem) {
		this.dem = dem;
	}

	/**
	 * @return the master
	 */
	public String getMaster() {
		return master;
	}

	/**
	 * @param master the master to set
	 */
	public void setMaster(String master) {
		this.master = master;
	}

	/**
	 * @return the epsgCodeNative
	 */
	public String getEpsgCode() {
		return epsgCodeNative;
	}

	/**
	 * @param epsgCodeNative the epsgCodeNative to set
	 */
	public void setEpsgCode(String epsgCode) {
		this.epsgCodeNative = epsgCode;
	}

	/**
	 * @return the orbitDirection
	 */
	public OrbitDirection getOrbitDirection() {
		return orbitDirection;
	}

	/**
	 * @param orbitDirection the orbitDirection to set
	 */
	public void setOrbitDirection(OrbitDirection orbitDirection) {
		this.orbitDirection = orbitDirection;
	}

	/**
	 * @return the orbitNumber
	 */
	public Integer getOrbitNumber() {
		return orbitNumber;
	}

	/**
	 * @param orbitNumber the orbitNumber to set
	 */
	public void setOrbitNumber(Integer orbitNumber) {
		this.orbitNumber = orbitNumber;
	}

	/**
	 * @return the width
	 */
	public Double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Double width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}

	/**
	 * @return the collection
	 */
	public Collection getCollection() {
		return collection;
	}

	/**
	 * @param collection the collection to set
	 */
	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	/**
	 * @param dataList the dataList to set
	 */
	public void setData(List<Data> data) {
		this.dataList = data;
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
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the privacy
	 */
	public Privacy getPrivacy() {
		return privacy;
	}

	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	/**
	 * Method to get GranuleUR from granule data
	 * 
	 * @return granuleUR (concatenation of collection and granule name)
	 */
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getGranuleUR() {
		this.granuleUr = "";
		if (this.getCollection() != null && this.getCollection().getShortName() != null && this.getName() != null) {
			this.granuleUr = this.getCollection().getShortName().toUpperCase() + Granule.GRANULE_ID_DELIMITER
					+ this.getName();
		}
		
		return this.granuleUr;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Granule granule = (Granule) o;
		return Objects.equals(this.id, granule.id) && Objects.equals(this.name, granule.name)
				&& Objects.equals(this.heading, granule.heading) && Objects.equals(this.zFlight, granule.zFlight)
				&& Objects.equals(this.zTerrain, granule.zTerrain) && Objects.equals(this.slrStart, granule.slrStart)
				&& Objects.equals(this.pixelSpacing, granule.pixelSpacing)
				&& Objects.equals(this.surfaceResol, granule.surfaceResol)
				&& Objects.equals(this.grdResol, granule.grdResol)
				&& Objects.equals(this.updateDate, granule.updateDate) && Objects.equals(this.dem, granule.dem)
				&& Objects.equals(this.master, granule.master)
				&& Objects.equals(this.epsgCodeDeclared, granule.epsgCodeDeclared)
				&& Objects.equals(this.epsgCodeNative, granule.epsgCodeNative)
				&& Objects.equals(this.orbitNumber, granule.orbitNumber) && Objects.equals(this.width, granule.width)
				&& Objects.equals(this.height, granule.height) && Objects.equals(this.platform, granule.platform)
				&& Objects.equals(this.quadrangle, granule.quadrangle)
				&& Objects.equals(this.polarization, granule.polarization)
				&& Objects.equals(this.subRegion, granule.subRegion) && Objects.equals(this.privacy, granule.privacy)
				&& Objects.equals(this.status, granule.status) && super.equals(o);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.heading, this.zFlight, this.zTerrain, this.slrStart,
				this.pixelSpacing, this.surfaceResol, this.grdResol, this.updateDate, this.dem, this.master,
				this.productType, this.epsgCodeDeclared, this.epsgCodeNative, this.orbitNumber, this.width, this.height,
				this.platform, this.quadrangle, this.polarization, this.subRegion, this.privacy, this.status,
				super.hashCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Granule {\n");
		sb.append("    ").append(Utils.toIndentedString(super.toString())).append("\n");
		sb.append("    id: ").append(Utils.toIndentedString(this.id)).append("\n");
		sb.append("    name: ").append(Utils.toIndentedString(this.name)).append("\n");
		sb.append("    heading: ").append(Utils.toIndentedString(this.heading)).append("\n");
		sb.append("    zFlight: ").append(Utils.toIndentedString(this.zFlight)).append("\n");
		sb.append("    zTerrain: ").append(Utils.toIndentedString(this.zTerrain)).append("\n");
		sb.append("    slrStart: ").append(Utils.toIndentedString(this.slrStart)).append("\n");
		sb.append("    pixelSpacing: ").append(Utils.toIndentedString(this.pixelSpacing)).append("\n");
		sb.append("    surfaceResol: ").append(Utils.toIndentedString(this.surfaceResol)).append("\n");
		sb.append("    grdResol: ").append(Utils.toIndentedString(this.grdResol)).append("\n");
		sb.append("    updateDate: ").append(Utils.toIndentedString(this.updateDate)).append("\n");
		sb.append("    dem: ").append(Utils.toIndentedString(this.dem)).append("\n");
		sb.append("    master: ").append(Utils.toIndentedString(this.master)).append("\n");
		sb.append("    productType: ").append(Utils.toIndentedString(this.productType)).append("\n");
		sb.append("    epsgCodeNative: ").append(Utils.toIndentedString(this.epsgCodeNative)).append("\n");
		sb.append("    epsgCodeDeclared: ").append(Utils.toIndentedString(this.epsgCodeDeclared)).append("\n");
		sb.append("    orbitNumber: ").append(Utils.toIndentedString(this.orbitNumber)).append("\n");
		sb.append("    width: ").append(Utils.toIndentedString(this.width)).append("\n");
		sb.append("    height: ").append(Utils.toIndentedString(this.height)).append("\n");
		sb.append("    platform: ").append(Utils.toIndentedString(this.platform)).append("\n");
		sb.append("    quadrangle: ").append(Utils.toIndentedString(this.quadrangle)).append("\n");
		sb.append("    granuleScene: ").append(Utils.toIndentedString(this.granuleScene)).append("\n");
		sb.append("    dataList: ").append(Utils.toIndentedString(this.dataList)).append("\n");
		sb.append("    polarization: ").append(Utils.toIndentedString(this.polarization)).append("\n");
		sb.append("    subRegion: ").append(Utils.toIndentedString(this.subRegion)).append("\n");
		sb.append("    BmaapUser: ").append(Utils.toIndentedString(this.author)).append("\n");
		sb.append("    privacy: ").append(Utils.toIndentedString(this.privacy)).append("\n");
		sb.append("    status: ").append(Utils.toIndentedString(this.status)).append("\n");
		sb.append("}");
		return sb.toString();

	}

}
