package edu.louisiana.cacs.nounclassifier.dao;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;

public class QueryExecutor {

	public static void executeQuery(String p_Query,Model model){
		Query query1 = QueryFactory.create(p_Query);
		QueryExecution qe = QueryExecutionFactory.create(query1, model);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query1);
		qe.close();
	}
	
	
	
	public static void executeQuery(String p_Query){
		Query query1 = QueryFactory.create(p_Query);
		QueryExecution qe = QueryExecutionFactory.sparqlService("//http://dbpedia.org/sparql", p_Query);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query1);
		qe.close();
	}
}
