package com.esa.bmap.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * CustomQuery
 * 
 * @author QFAURE
 *
 */
public class CustomQuery {

	/**
	 * Target table for the query.
	 */
	private String tableName = null;

	/*
	 * Joined tables for the query.
	 */
	private List<String> joinedTablesList = null;

	/**
	 * Criterion for the query.
	 */
	private List<String> criterionList = null;

	/**
	 * Logger for the class.
	 */
	private Logger log = LoggerFactory.getLogger(CustomQuery.class);

	/**
	 * Create an empty custom query.
	 */
	public CustomQuery() {
		super();
		this.joinedTablesList = new ArrayList<>();
		this.criterionList = new ArrayList<>();
	}

	/**
	 * Create a custom query with the specified table name.
	 * 
	 * @param tableName
	 * Target table for the query.
	 * @throws BmapException
	 * When the specified string did not pass the security checks.
	 */
	public CustomQuery(String tableName) throws BmapException {
		super();
		this.tableName = tableName;
		this.joinedTablesList = new ArrayList<>();
		this.criterionList = new ArrayList<>();
	}

	/**
	 * @return The tableName.
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 * The tableName to set.
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return The joinedTablesList.
	 */
	public List<String> getJoinedTablesList() {
		return joinedTablesList;
	}

	/**
	 * @param joinedTablesList
	 * The joinedTablesList to set.
	 */
	public void setJoinedTablesList(List<String> joinedTablesList) {
		this.joinedTablesList = joinedTablesList;
	}

	/**
	 * @return The criterionList.
	 */
	public List<String> getCriterionList() {
		return criterionList;
	}

	/**
	 * @param criterionList
	 * The criterionList to set.
	 */
	public void setCriterionList(List<String> criterionList) {
		this.criterionList = criterionList;
	}

	/**
	 * Adds a joined table to the list.
	 * 
	 * @param tableName
	 * The joined table to be added to the list
	 * @throws BmapException
	 * When the specified string did not pass the security checks.
	 */
	public void addJoinedTableToList(String joinedTable) throws BmapException {
		if (!this.joinedTablesList.contains(joinedTable))
			this.joinedTablesList.add(joinedTable);
	}

	/**
	 * Adds a criteria to the list.
	 * 
	 * @param criteria
	 * The criteria to be added to the list.
	 * @throws BmapException
	 * When the specified string did not pass the security checks.
	 */
	public void addCriteriaToList(String criteria) throws BmapException {
		if (!this.criterionList.contains(criteria))
			this.criterionList.add(criteria);
	}

	/**
	 * Builds a string of values from the specified list (e.g. "'A', 'B', 'C'").
	 * 
	 * @param list
	 * The list of strings from which to build the string of values.
	 * @return The string of values built from the list of strings.
	 * @throws BmapException
	 * When the specified string did not pass the security checks.
	 */
	public static String buildValuesString(List<String> list) throws BmapException {
		String values = "";
		for (String currentString : list) {
			if (values.length() != 0)
				values += ", ";
			values += "'" + currentString + "'";
		}
		return values;
	}

	/**
	 * Returns the processed query string.
	 * 
	 * @return The query string built from the set attributes.
	 */
	@Override
	public String toString() {
		// only return the string only if the query is fully formed
		if (this.tableName != null && !this.criterionList.isEmpty()) {
			String query = "select * from " + tableName;
			for (String currentString : joinedTablesList) {
				query += " " + currentString;
			}
			for (int i = 0; i < criterionList.size(); i++) {
				if (i == 0) {
					query += " where " + criterionList.get(i);
				} else {
					query += " and " + criterionList.get(i);
				}
			}
			this.log.info(query);
			return query;
		} else {
			return "";
		}
	}
}
