package edu.louisiana.cacs.nounclassifier.queries;

import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.rdf.model.Model;

import edu.louisiana.cacs.nounclassifier.dao.QueryExecutor;

public class SampleQueries {

	public void execute() {

		Model model = RDFDataMgr.loadModel("example1.ttl");

		String queryString1 = "PREFIX ab: <http://learningsparql.com/ns/addressbook#> "
				+ "SELECT ?craigEmail"
				+ "	WHERE "
				+ "	{ ab:craig ab:email ?craigEmail . } ";

		String queryString2 = "PREFIX ab: <http://learningsparql.com/ns/addressbook#>"
				+ " SELECT ?person "
				+ " WHERE { ?person ab:homeTel \"(229) 276-5135\" . } ";

		QueryExecutor.executeQuery(queryString1, model);
		QueryExecutor.executeQuery(queryString2, model);
	}

}
