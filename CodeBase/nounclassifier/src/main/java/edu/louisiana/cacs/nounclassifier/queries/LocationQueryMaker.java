package edu.louisiana.cacs.nounclassifier.queries;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LocationQueryMaker {
	
	private static Log m_logger = LogFactory.getLog(LocationQueryMaker.class);
	
	/**
	 * Makes a query to fetch entity type for the given person name.
	 * 
	 * @param p_locationName
	 *            - A <code>String</code> representing the person name of whose
	 *            entity type has to be fetched.
	 * @param p_language
	 *            - A <code>String</code> representing the language.
	 * @return xEntityTypeQuery - A <code>String</code> representing the query.
	 * @version 1.0
	 */
	public static String getEntityType(String p_locationName, String p_language) {
		String xEntityTypeQuery = "SELECT ?entityType "
				+ "WHERE "
				+ "{ "
				+ "?person <http://xmlns.com/foaf/0.1/name> \""
				+ p_locationName
				+ p_language
				+ "?person <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entityType .  }";
		if (m_logger.isTraceEnabled()) {
			m_logger.trace("Entity Query:" + xEntityTypeQuery);
		}
		return xEntityTypeQuery;
	}
	
}
