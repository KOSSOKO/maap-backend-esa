package com.esa.bmap.dao.catalogue.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.CustomQuery;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.Polarization;
import com.esa.bmap.model.Privacy;

@Service
public class DataDao {

	@PersistenceContext
	EntityManager entityManager;

	private static final Logger LOG = LoggerFactory.getLogger(DataDao.class);

	/**
	 * @param granuleCriteria
	 * @return
	 * @throws BmapException
	 */
	public Collection<Granule> findByCriteria(GranuleCriteria granuleCriteria) throws BmapException {

		// the data collection to return
		Collection<Granule> dataCollection = new ArrayList<>();

		LOG.info(granuleCriteria.toString());
		// instantiation of custom queries
		CustomQuery granuleCustomQuery = new CustomQuery("granule");

		// granule name
		if (granuleCriteria.getGranuleName() != null) {
			// add criteria
			granuleCustomQuery.addCriteriaToList("granule.name ='" + granuleCriteria.getGranuleName() + "'");

		}

		// sub-region name
		if (granuleCriteria.getSubRegionNames() != null) {
			// add joined tables
			granuleCustomQuery.addJoinedTableToList("inner join sub_region on granule.sub_region_id = sub_region.id");

			// add criteria
			granuleCustomQuery.addCriteriaToList(
					"sub_region.name in(" + CustomQuery.buildValuesString(granuleCriteria.getSubRegionNames()) + ")");

		}
		// collection name
		if (granuleCriteria.getCollectionNames() != null) {

			granuleCriteria.getCollectionNames().replaceAll(e -> e.toUpperCase());
			// add joined tables
			granuleCustomQuery.addJoinedTableToList("inner join collection on granule.collection_id = collection.id");

			// add criteria
			if (granuleCriteria.getCollectionNames().size() == 1
					&& granuleCriteria.getCollectionNames().get(0).indexOf("*") >= 0) {
				// use the collection name having stars as a pattern in the request
				String collectionNamePatternCriteria = granuleCriteria.getCollectionNames().get(0);
				collectionNamePatternCriteria = collectionNamePatternCriteria.replaceAll("\\*", "%");
				granuleCustomQuery.addCriteriaToList("UPPER( collection.short_name ) like '"
						+ collectionNamePatternCriteria + "'");
			} else {
				// use criteria as a simple list of collection name
			granuleCustomQuery.addCriteriaToList("UPPER( collection.short_name ) in("
					+ CustomQuery.buildValuesString(granuleCriteria.getCollectionNames()) + ")");
			}

		}

		// start date and end date
		if (granuleCriteria.getStartDate() != null && granuleCriteria.getEndDate() != null) {
			// add criteria
			granuleCustomQuery
					.addJoinedTableToList("INNER JOIN granule_data_list ON granule.id = granule_data_list.granule_id");
			granuleCustomQuery
					.addJoinedTableToList("INNER JOIN public.data ON granule_data_list.data_list_id = public.data.id");
			granuleCustomQuery.addCriteriaToList("public.data.acquisition_date between '"
					+ granuleCriteria.getStartDate() + "' and '" + granuleCriteria.getEndDate() + "'");

		}

		// productTypes name
		if (granuleCriteria.getProductTypes() != null) {

			// add criteria
			granuleCustomQuery.addCriteriaToList("granule.product_type in("
					+ CustomQuery.buildValuesString((granuleCriteria.getProductTypes())) + ")");
		}

		// polarization
		if (granuleCriteria.getPolarizations() != null) {
			// transform the list of polarizations into a list of strings
			List<String> polarizations = new ArrayList<>();
			for (Polarization currentPolarization : granuleCriteria.getPolarizations()) {
				polarizations.add(currentPolarization.toString());
			}
			// add criteria
			granuleCustomQuery
					.addCriteriaToList("granule.polarization in(" + CustomQuery.buildValuesString(polarizations) + ")");
		}

		// instrumentNames name
		if (granuleCriteria.getInstrumentNames() != null) {
			// add joined tables
			granuleCustomQuery.addJoinedTableToList("inner join platform on granule.platform_id=platform.id");
			granuleCustomQuery.addJoinedTableToList(
					"inner join platform_list_instrument on platform.id=platform_list_instrument.platform_id");
			granuleCustomQuery.addJoinedTableToList(
					"inner join instrument on platform_list_instrument.list_instrument_id=instrument.id");

			// add criteria
			granuleCustomQuery.addCriteriaToList("instrument.name in("
					+ CustomQuery.buildValuesString((granuleCriteria.getInstrumentNames())) + ")");
		}

		// geometry type
		if (granuleCriteria.getGeometryTypes() != null) {
			// add criteria

			granuleCustomQuery.addCriteriaToList(
					"exists (SELECT gdata.id FROM granule gdata, granule_data_list gdatalist, public.data where gdata.id=gdatalist.granule_id and gdatalist.data_list_id=public.data.id and gdata.id=granule.id and public.data.geometry_type in("
							+ CustomQuery.buildValuesString(granuleCriteria.getGeometryTypes()) + "))");
		}

		// processing level
		if (granuleCriteria.getProcessingLevels() != null) {
			// add criteria
			granuleCustomQuery.addJoinedTableToList("inner join collection on granule.collection_id = collection.id");
			granuleCustomQuery.addCriteriaToList("collection.processing_level in("
					+ CustomQuery.buildValuesString(granuleCriteria.getProcessingLevels()) + ")");
		}

		// location
		if (granuleCriteria.getLocation() != null) {
			// add joined tables
			granuleCustomQuery.addJoinedTableToList("inner join quadrangle on granule.quadrangle_id = quadrangle.id");

			// add criteria
			granuleCustomQuery
					.addCriteriaToList("st_intersects(quadrangle.geometry, st_makevalid(st_geomfromtext('srid=4326; "
							+ granuleCriteria.getLocation().getGeometry().toText()
							+ "'))) or quadrangle.geometry is null");
		}

		// heading value
		if (granuleCriteria.getHeadingValues() != null) {

			granuleCustomQuery.addCriteriaToList(
					"exists(select * from granule gscene where gscene.id=granule.granule_scene_id and gscene.heading in("
							+ CustomQuery.buildValuesString(granuleCriteria.getHeadingValues()) + "))");

		}
		// checking desired privacy of granules
		if (granuleCriteria.getPrivacy() != null && granuleCriteria.getPrivacy().equals(Privacy.PRIVATE)) {
			if (granuleCriteria.getUserId() != null) {
				granuleCustomQuery.addCriteriaToList("granule.privacy='" + granuleCriteria.getPrivacy()
				+ "' AND granule.id_user=" + granuleCriteria.getUserId() + "");
			} else {
				granuleCustomQuery.addCriteriaToList("granule.privacy='" + granuleCriteria.getPrivacy()
				+ "'");
			}
			

		} else {
			granuleCustomQuery.addCriteriaToList("granule.privacy <> '" + Privacy.PRIVATE + "'");
		}

		// add the custom queries to an array list
		ArrayList<CustomQuery> customQueriesList = new ArrayList<>();
		customQueriesList.add(granuleCustomQuery);

		// for each custom query
		for (CustomQuery currentCustomQuery : customQueriesList) {
			String currentCustomQueryString = currentCustomQuery.toString();
			// if the custom query is fully formed
			if (currentCustomQueryString.length() > 0) {
				// result list
				List<?> resultList = null;
				// execute the custom query and get the result list depending on the target
				// table name
				LOG.info(currentCustomQueryString);
				switch (currentCustomQuery.getTableName()) {
				case "granule":
					resultList = entityManager.createNativeQuery(currentCustomQueryString, Granule.class)
							.getResultList();
					break;
				default:
					LOG.error(Common.getMessageValue("datadao.findbycriteria.unexpectedtablename.error"));
					throw new BmapException(Common.getMessageValue("datadao.findbycriteria.unexpectedtablename.error"));
				}
				if (!resultList.isEmpty()) {
					// for each entry in the result list, add it to the collection to return
					for (int i = 0; i < resultList.size(); i++) {
						dataCollection.add((Granule) resultList.get(i));
					}
				}
			}
		}

		return dataCollection;
	}
}
