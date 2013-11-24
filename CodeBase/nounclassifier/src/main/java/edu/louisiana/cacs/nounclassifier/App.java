package edu.louisiana.cacs.nounclassifier;

import java.io.File;
import java.io.IOException;

import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.rdf.model.Model;

import edu.louisiana.cacs.nounclassifier.dao.QueryExecutor;
import edu.louisiana.cacs.nounclassifier.util.Configurator;

/**
 * <p>
 * Main class which calls the algorithm
 * 
 * </p>
 * <p>
 * It also configures the system by reading the nounclassifier.properties.
 * <p>
 * 
 * @author rsunkara
 * @since November 2,2013
 * 
 */
public class App {
	public static void main(String[] args) throws IOException {

		Configurator xConfigurator = new Configurator();
		String xPropertiesFilePath = System.getProperty("user.dir")
				+ File.separator + "src" + File.separator + "main"
				+ File.separator + "resources" + File.separator
				+ "nounclassifier.properties";
		// If any error in system configuration, stop the system
		if (!xConfigurator.loadConfigValues(xPropertiesFilePath))
			return;
		
		String personName = "Aristotle";

		String query4 = "SELECT ?desc ?birthPlace ?entityType "
				+ "WHERE "
				+ "{ "
				+ "?person <http://xmlns.com/foaf/0.1/name> \""+personName+"\"@en . "
				+ "?person <http://purl.org/dc/elements/1.1/description> ?desc . "
				+ "?person <http://dbpedia.org/ontology/birthPlace> ?birthPlace . "
				+ "?person <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?entityType . " 
				+ "FILTER ( lang(?desc) = \"en\" ) }";

		Model model2 = RDFDataMgr.loadModel("sample.nt");
		System.out.println("Model loaded");
		QueryExecutor.executeQuery(query4, model2);

	}

}
