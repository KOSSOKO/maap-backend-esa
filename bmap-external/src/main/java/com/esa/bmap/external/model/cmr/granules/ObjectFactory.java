//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.21 à 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.esa.bmap.external.model.cmr.granules package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GranuleMetaDataFile_QNAME = new QName("", "GranuleMetaDataFile");
    private final static QName _GranulePartialDelete_QNAME = new QName("", "GranulePartialDelete");
    private final static QName _GranuleUR_QNAME = new QName("", "GranuleUR");
    private final static QName _GranulePartialAdd_QNAME = new QName("", "GranulePartialAdd");
    private final static QName _Granule_QNAME = new QName("", "Granule");
    private final static QName _GranuleDelete_QNAME = new QName("", "GranuleDelete");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.esa.bmap.external.model.cmr.granules
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GranuleMetaDataFile }
     * 
     */
    public GranuleMetaDataFile createGranuleMetaDataFile() {
        return new GranuleMetaDataFile();
    }

    /**
     * Create an instance of {@link GranulePartialDelete }
     * 
     */
    public GranulePartialDelete createGranulePartialDelete() {
        return new GranulePartialDelete();
    }

    /**
     * Create an instance of {@link GranulePartialAdd }
     * 
     */
    public GranulePartialAdd createGranulePartialAdd() {
        return new GranulePartialAdd();
    }

    /**
     * Create an instance of {@link Granule }
     * 
     */
    public Granule createGranule() {
        return new Granule();
    }

    /**
     * Create an instance of {@link GranuleDelete }
     * 
     */
    public GranuleDelete createGranuleDelete() {
        return new GranuleDelete();
    }

    /**
     * Create an instance of {@link ListOfOnlineAccessURLs }
     * 
     */
    public ListOfOnlineAccessURLs createListOfOnlineAccessURLs() {
        return new ListOfOnlineAccessURLs();
    }

    /**
     * Create an instance of {@link ListOfGranules }
     * 
     */
    public ListOfGranules createListOfGranules() {
        return new ListOfGranules();
    }

    /**
     * Create an instance of {@link ListOfOnlineResources }
     * 
     */
    public ListOfOnlineResources createListOfOnlineResources() {
        return new ListOfOnlineResources();
    }

    /**
     * Create an instance of {@link ListOfAdditionalAttributeRefs }
     * 
     */
    public ListOfAdditionalAttributeRefs createListOfAdditionalAttributeRefs() {
        return new ListOfAdditionalAttributeRefs();
    }

    /**
     * Create an instance of {@link ListOfGranuleAddFields }
     * 
     */
    public ListOfGranuleAddFields createListOfGranuleAddFields() {
        return new ListOfGranuleAddFields();
    }

    /**
     * Create an instance of {@link QAFlags }
     * 
     */
    public QAFlags createQAFlags() {
        return new QAFlags();
    }

    /**
     * Create an instance of {@link PlatformRef }
     * 
     */
    public PlatformRef createPlatformRef() {
        return new PlatformRef();
    }

    /**
     * Create an instance of {@link GPolygon }
     * 
     */
    public GPolygon createGPolygon() {
        return new GPolygon();
    }

    /**
     * Create an instance of {@link ListOfPlatformRefs }
     * 
     */
    public ListOfPlatformRefs createListOfPlatformRefs() {
        return new ListOfPlatformRefs();
    }

    /**
     * Create an instance of {@link VerticalSpatialDomain }
     * 
     */
    public VerticalSpatialDomain createVerticalSpatialDomain() {
        return new VerticalSpatialDomain();
    }

    /**
     * Create an instance of {@link Boundary }
     * 
     */
    public Boundary createBoundary() {
        return new Boundary();
    }

    /**
     * Create an instance of {@link RangeDateTime }
     * 
     */
    public RangeDateTime createRangeDateTime() {
        return new RangeDateTime();
    }

    /**
     * Create an instance of {@link ListOfInstrumentRefs }
     * 
     */
    public ListOfInstrumentRefs createListOfInstrumentRefs() {
        return new ListOfInstrumentRefs();
    }

    /**
     * Create an instance of {@link MeasuredParameter }
     * 
     */
    public MeasuredParameter createMeasuredParameter() {
        return new MeasuredParameter();
    }

    /**
     * Create an instance of {@link AdditionalAttributeRef }
     * 
     */
    public AdditionalAttributeRef createAdditionalAttributeRef() {
        return new AdditionalAttributeRef();
    }

    /**
     * Create an instance of {@link ListOfOperationModes }
     * 
     */
    public ListOfOperationModes createListOfOperationModes() {
        return new ListOfOperationModes();
    }

    /**
     * Create an instance of {@link ListOfGranulePartialDeletes }
     * 
     */
    public ListOfGranulePartialDeletes createListOfGranulePartialDeletes() {
        return new ListOfGranulePartialDeletes();
    }

    /**
     * Create an instance of {@link Orbit }
     * 
     */
    public Orbit createOrbit() {
        return new Orbit();
    }

    /**
     * Create an instance of {@link GranuleUpdateTarget }
     * 
     */
    public GranuleUpdateTarget createGranuleUpdateTarget() {
        return new GranuleUpdateTarget();
    }

    /**
     * Create an instance of {@link ListOfGranuleUpdateTargets }
     * 
     */
    public ListOfGranuleUpdateTargets createListOfGranuleUpdateTargets() {
        return new ListOfGranuleUpdateTargets();
    }

    /**
     * Create an instance of {@link ListOfStrings }
     * 
     */
    public ListOfStrings createListOfStrings() {
        return new ListOfStrings();
    }

    /**
     * Create an instance of {@link HorizontalSpatialDomain }
     * 
     */
    public HorizontalSpatialDomain createHorizontalSpatialDomain() {
        return new HorizontalSpatialDomain();
    }

    /**
     * Create an instance of {@link Line }
     * 
     */
    public Line createLine() {
        return new Line();
    }

    /**
     * Create an instance of {@link OnlineResource }
     * 
     */
    public OnlineResource createOnlineResource() {
        return new OnlineResource();
    }

    /**
     * Create an instance of {@link InstrumentRef }
     * 
     */
    public InstrumentRef createInstrumentRef() {
        return new InstrumentRef();
    }

    /**
     * Create an instance of {@link ListOfOrbitCalculatedSpatialDomains }
     * 
     */
    public ListOfOrbitCalculatedSpatialDomains createListOfOrbitCalculatedSpatialDomains() {
        return new ListOfOrbitCalculatedSpatialDomains();
    }

    /**
     * Create an instance of {@link ListOfCharacteristicRefs }
     * 
     */
    public ListOfCharacteristicRefs createListOfCharacteristicRefs() {
        return new ListOfCharacteristicRefs();
    }

    /**
     * Create an instance of {@link Spatial }
     * 
     */
    public Spatial createSpatial() {
        return new Spatial();
    }

    /**
     * Create an instance of {@link ListOfProviderBrowseIds }
     * 
     */
    public ListOfProviderBrowseIds createListOfProviderBrowseIds() {
        return new ListOfProviderBrowseIds();
    }

    /**
     * Create an instance of {@link ProviderBrowseUrl }
     * 
     */
    public ProviderBrowseUrl createProviderBrowseUrl() {
        return new ProviderBrowseUrl();
    }

    /**
     * Create an instance of {@link ListOfSensorRefs }
     * 
     */
    public ListOfSensorRefs createListOfSensorRefs() {
        return new ListOfSensorRefs();
    }

    /**
     * Create an instance of {@link ListOfCollectionRefs }
     * 
     */
    public ListOfCollectionRefs createListOfCollectionRefs() {
        return new ListOfCollectionRefs();
    }

    /**
     * Create an instance of {@link ExclusiveZone }
     * 
     */
    public ExclusiveZone createExclusiveZone() {
        return new ExclusiveZone();
    }

    /**
     * Create an instance of {@link ListOfGranuleDeletes }
     * 
     */
    public ListOfGranuleDeletes createListOfGranuleDeletes() {
        return new ListOfGranuleDeletes();
    }

    /**
     * Create an instance of {@link ListOfGranuleDeleteFields }
     * 
     */
    public ListOfGranuleDeleteFields createListOfGranuleDeleteFields() {
        return new ListOfGranuleDeleteFields();
    }

    /**
     * Create an instance of {@link BoundingRectangle }
     * 
     */
    public BoundingRectangle createBoundingRectangle() {
        return new BoundingRectangle();
    }

    /**
     * Create an instance of {@link EmptyType }
     * 
     */
    public EmptyType createEmptyType() {
        return new EmptyType();
    }

    /**
     * Create an instance of {@link PGEVersionClass }
     * 
     */
    public PGEVersionClass createPGEVersionClass() {
        return new PGEVersionClass();
    }

    /**
     * Create an instance of {@link GranuleAddField }
     * 
     */
    public GranuleAddField createGranuleAddField() {
        return new GranuleAddField();
    }

    /**
     * Create an instance of {@link ListOfGranulePartialAdds }
     * 
     */
    public ListOfGranulePartialAdds createListOfGranulePartialAdds() {
        return new ListOfGranulePartialAdds();
    }

    /**
     * Create an instance of {@link ListOfProviderBrowseUrls }
     * 
     */
    public ListOfProviderBrowseUrls createListOfProviderBrowseUrls() {
        return new ListOfProviderBrowseUrls();
    }

    /**
     * Create an instance of {@link Temporal }
     * 
     */
    public Temporal createTemporal() {
        return new Temporal();
    }

    /**
     * Create an instance of {@link ListOfVerticalSpatialDomains }
     * 
     */
    public ListOfVerticalSpatialDomains createListOfVerticalSpatialDomains() {
        return new ListOfVerticalSpatialDomains();
    }

    /**
     * Create an instance of {@link ProviderBrowseElement }
     * 
     */
    public ProviderBrowseElement createProviderBrowseElement() {
        return new ProviderBrowseElement();
    }

    /**
     * Create an instance of {@link SensorRef }
     * 
     */
    public SensorRef createSensorRef() {
        return new SensorRef();
    }

    /**
     * Create an instance of {@link CollectionRef }
     * 
     */
    public CollectionRef createCollectionRef() {
        return new CollectionRef();
    }

    /**
     * Create an instance of {@link CampaignRef }
     * 
     */
    public CampaignRef createCampaignRef() {
        return new CampaignRef();
    }

    /**
     * Create an instance of {@link GranuleInventory }
     * 
     */
    public GranuleInventory createGranuleInventory() {
        return new GranuleInventory();
    }

    /**
     * Create an instance of {@link CharacteristicRef }
     * 
     */
    public CharacteristicRef createCharacteristicRef() {
        return new CharacteristicRef();
    }

    /**
     * Create an instance of {@link ListOfCampaignRefs }
     * 
     */
    public ListOfCampaignRefs createListOfCampaignRefs() {
        return new ListOfCampaignRefs();
    }

    /**
     * Create an instance of {@link ListOfGranuleUpdateTargetFields }
     * 
     */
    public ListOfGranuleUpdateTargetFields createListOfGranuleUpdateTargetFields() {
        return new ListOfGranuleUpdateTargetFields();
    }

    /**
     * Create an instance of {@link QAStats }
     * 
     */
    public QAStats createQAStats() {
        return new QAStats();
    }

    /**
     * Create an instance of {@link ListOfGranuleInventories }
     * 
     */
    public ListOfGranuleInventories createListOfGranuleInventories() {
        return new ListOfGranuleInventories();
    }

    /**
     * Create an instance of {@link ListOfGranuleURs }
     * 
     */
    public ListOfGranuleURs createListOfGranuleURs() {
        return new ListOfGranuleURs();
    }

    /**
     * Create an instance of {@link OrbitCalculatedSpatialDomain }
     * 
     */
    public OrbitCalculatedSpatialDomain createOrbitCalculatedSpatialDomain() {
        return new OrbitCalculatedSpatialDomain();
    }

    /**
     * Create an instance of {@link ListOfLocalityValues }
     * 
     */
    public ListOfLocalityValues createListOfLocalityValues() {
        return new ListOfLocalityValues();
    }

    /**
     * Create an instance of {@link Point }
     * 
     */
    public Point createPoint() {
        return new Point();
    }

    /**
     * Create an instance of {@link ListOfInputGranules }
     * 
     */
    public ListOfInputGranules createListOfInputGranules() {
        return new ListOfInputGranules();
    }

    /**
     * Create an instance of {@link ListOfMeasuredParameters }
     * 
     */
    public ListOfMeasuredParameters createListOfMeasuredParameters() {
        return new ListOfMeasuredParameters();
    }

    /**
     * Create an instance of {@link ListOfAdditionalAttributeValues }
     * 
     */
    public ListOfAdditionalAttributeValues createListOfAdditionalAttributeValues() {
        return new ListOfAdditionalAttributeValues();
    }

    /**
     * Create an instance of {@link Geometry }
     * 
     */
    public Geometry createGeometry() {
        return new Geometry();
    }

    /**
     * Create an instance of {@link TwoDCoordinateSystem }
     * 
     */
    public TwoDCoordinateSystem createTwoDCoordinateSystem() {
        return new TwoDCoordinateSystem();
    }

    /**
     * Create an instance of {@link GranuleDeleteField }
     * 
     */
    public GranuleDeleteField createGranuleDeleteField() {
        return new GranuleDeleteField();
    }

    /**
     * Create an instance of {@link DoiType }
     * 
     */
    public DoiType createDoiType() {
        return new DoiType();
    }

    /**
     * Create an instance of {@link DataGranule }
     * 
     */
    public DataGranule createDataGranule() {
        return new DataGranule();
    }

    /**
     * Create an instance of {@link OnlineAccessURL }
     * 
     */
    public OnlineAccessURL createOnlineAccessURL() {
        return new OnlineAccessURL();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GranuleMetaDataFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GranuleMetaDataFile")
    public JAXBElement<GranuleMetaDataFile> createGranuleMetaDataFile(GranuleMetaDataFile value) {
        return new JAXBElement<GranuleMetaDataFile>(_GranuleMetaDataFile_QNAME, GranuleMetaDataFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GranulePartialDelete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GranulePartialDelete")
    public JAXBElement<GranulePartialDelete> createGranulePartialDelete(GranulePartialDelete value) {
        return new JAXBElement<GranulePartialDelete>(_GranulePartialDelete_QNAME, GranulePartialDelete.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GranuleUR")
    public JAXBElement<String> createGranuleUR(String value) {
        return new JAXBElement<String>(_GranuleUR_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GranulePartialAdd }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GranulePartialAdd")
    public JAXBElement<GranulePartialAdd> createGranulePartialAdd(GranulePartialAdd value) {
        return new JAXBElement<GranulePartialAdd>(_GranulePartialAdd_QNAME, GranulePartialAdd.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Granule }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Granule")
    public JAXBElement<Granule> createGranule(Granule value) {
        return new JAXBElement<Granule>(_Granule_QNAME, Granule.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GranuleDelete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "GranuleDelete")
    public JAXBElement<GranuleDelete> createGranuleDelete(GranuleDelete value) {
        return new JAXBElement<GranuleDelete>(_GranuleDelete_QNAME, GranuleDelete.class, null, value);
    }

}
