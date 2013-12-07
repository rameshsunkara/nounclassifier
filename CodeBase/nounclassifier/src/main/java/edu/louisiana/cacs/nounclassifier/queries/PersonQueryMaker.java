package edu.louisiana.cacs.nounclassifier.queries;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Makes queries to fetch data from person data file provided by DBPedia.
 * </p>
 * 
 * @since November 24, 2013
 * @author rsunkara
 * @version 1.0
 * 
 */
public class PersonQueryMaker {

	private static Log m_logger = LogFactory.getLog(PersonQueryMaker.class);

	/**
	 * Makes a query to fetch entity type for the given person name.
	 * 
	 * @param p_personName
	 *            - A <code>String</code> representing the person name of whose
	 *            entity type has to be fetched.
	 * @param p_language
	 *            - A <code>String</code> representing the language.
	 * @return xEntityTypeQuery - A <code>String</code> representing the query.
	 * @version 1.0
	 */
	public static String getEntityType(String p_personName, String p_language) {
		String xEntityTypeQuery = "SELECT ?entityType "
				+ "WHERE "
				+ "{ "
				+ "?person <http://xmlns.com/foaf/0.1/name> \""
				+ p_personName
				+ p_language
				+ "?person <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entityType .  }";
		if (m_logger.isTraceEnabled()) {
			m_logger.trace("Entity Query:" + xEntityTypeQuery);
		}
		return xEntityTypeQuery;
	}

	/**
	 * Makes a query to fetch birth place for the given person name.
	 * 
	 * @param p_personName
	 *            - A <code>String</code> representing the person name of whose
	 *            birth place has to be fetched.
	 * @param p_language
	 *            - A <code>String</code> representing the language.
	 * @return xBirthPlaceQuery - A <code>String</code> representing the query.
	 * @version 1.0
	 */
	public static String getBirthPlace(String p_personName, String p_language) {
		String xBirthPlaceQuery = "SELECT ?birthPlace "
				+ "WHERE "
				+ "{ "
				+ "?person <http://xmlns.com/foaf/0.1/name> \""
				+ p_personName
				+ p_language
				+ "?person <http://dbpedia.org/ontology/birthPlace> ?birthPlace .  }";
		if (m_logger.isTraceEnabled()) {
			m_logger.trace("Birth Place Query:" + xBirthPlaceQuery);
		}
		return xBirthPlaceQuery;
	}

	/**
	 * Makes a query to fetch death place for the given person name.
	 * 
	 * @param p_personName
	 *            - A <code>String</code> representing the person name of whose
	 *            death place has to be fetched.
	 * @param p_language
	 *            - A <code>String</code> representing the language.
	 * @return xDeathPlaceQuery - A <code>String</code> representing the query.
	 * @version 1.0
	 */
	public static String getDeathPlace(String p_personName, String p_language) {
		String xDeathPlaceQuery = "SELECT ?deathPlace "
				+ "WHERE "
				+ "{ "
				+ "?person <http://xmlns.com/foaf/0.1/name> \""
				+ p_personName
				+ p_language
				+ "?person <http://dbpedia.org/ontology/deathPlace> ?deathPlace .  }";
		if (m_logger.isTraceEnabled()) {
			m_logger.trace("Death Place Query:" + xDeathPlaceQuery);
		}
		return xDeathPlaceQuery;
	}

	
	/**
	 * Makes a query to fetch description for the given person name.
	 * 
	 * @param p_personName
	 *            - A <code>String</code> representing the person name of whose
	 *            description has to be fetched.
	 * @param p_language
	 *            - A <code>String</code> representing the language.
	 * @return xPersonDescQuery - A <code>String</code> representing the query.
	 * @version 1.0
	 */
	public static String getDescription(String p_personName, String p_language) {
		String xPersonDescQuery = "SELECT ?desc "
				+ "WHERE "
				+ "{ "
				+ "?person <http://xmlns.com/foaf/0.1/name> \""
				+ p_personName
				+ p_language
				+ "?person <http://purl.org/dc/elements/1.1/description> ?desc .  }";
		if (m_logger.isTraceEnabled()) {
			m_logger.trace("Person Description Query:" + xPersonDescQuery);
		}
		return xPersonDescQuery;
	}

	

}
