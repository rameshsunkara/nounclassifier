package edu.louisiana.cacs.nounclassifier.dao;

import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * This class contains methods to executes given Queries.
 * @author rsunkara
 *
 */
public class QueryExecutor {

	/**
	 * Executes the given query on the model passed.
	 * 
	 * @param p_Query
	 *            - A <String> representing the query to be executed.
	 * @param model
	 *            - A <com.hp.hpl.jena.rdf.model.Model> object on which the
	 *            query will be executed.
	 * @return List<QuerySolution>
	 * @version 1.0
	 * @return 
	 */
	public static List<QuerySolution> executeQuery(String p_Query, Model model) {
		Query query = QueryFactory.create(p_Query);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		List<QuerySolution> res = ResultSetFormatter.toList(results);
		qe.close();
		return res;
	}
	
	
	/**
	 * Executes the given query on the model passed and print the output as String.
	 * 
	 * @param p_Query
	 *            - A <String> representing the query to be executed.
	 * @param model
	 *            - A <com.hp.hpl.jena.rdf.model.Model> object on which the
	 *            query will be executed.
	 * @version 1.0
	 * @return 
	 */
	public static void executeQueryAsString(String p_Query, Model model) {
		Query query = QueryFactory.create(p_Query);
		QueryExecution qe = QueryExecutionFactory.create(query, model);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query);
		qe.close();
	}
	

	/**
	 * Executes the given query on SPARQL Service.
	 * 
	 * @param p_Query
	 * 			 - A <String> representing the query to be executed.
	 * @version 1.0
	 */
	public static void executeQuery(String p_Query) {
		Query query1 = QueryFactory.create(p_Query);
		QueryExecution qe = QueryExecutionFactory.sparqlService(
				"//http://dbpedia.org/sparql", p_Query);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query1);
		qe.close();
	}
}
