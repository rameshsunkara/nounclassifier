package edu.louisiana.cacs.nounclassifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.Model;

import edu.louisiana.cacs.nounclassifier.dao.QueryExecutor;
import edu.louisiana.cacs.nounclassifier.exception.NounClassifierException;
import edu.louisiana.cacs.nounclassifier.queries.GenericQueryMaker;
import edu.louisiana.cacs.nounclassifier.queries.PersonQueryMaker;
import edu.louisiana.cacs.nounclassifier.util.Configurator;
import edu.louisiana.cacs.nounclassifier.util.NounClassifierConstants;

/**
 * @author rsunkara
 * 
 */
public class NounClassifier implements INounClassifier {

	private static Log m_logger = LogFactory.getLog(NounClassifier.class);

	private Configurator m_Configurator = null;

	private Model m_DataModel = null;

	public NounClassifier(Configurator p_Configurator) {
		m_Configurator = p_Configurator;
	}

	public void classify() throws NounClassifierException {
		m_logger.trace("Enter classify()");
		Scanner scanner = new Scanner(System.in);
		String xSearchTerm = "";
		m_logger.info("Enter the search term which has to be searched for. XXX to stop searching");
		xSearchTerm = scanner.next();
		PrintWriter xEvaluationResultWriter = null;

		while (!xSearchTerm.equalsIgnoreCase("xxx")) {
			try {
				xEvaluationResultWriter = new PrintWriter(new File(
						m_Configurator.get_output_dir() + File.separator
								+ xSearchTerm));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			//Search in person data
			 /*queryPersonData(xEvaluationResultWriter, xSearchTerm);
			 m_DataModel = null;*/
			 
			//Search in location data
			/*queryLocationData(xEvaluationResultWriter, xSearchTerm);
			m_DataModel = null;*/
			
			//Search in disambiguation data
			
			queryDisamgData(xEvaluationResultWriter, xSearchTerm);
			m_DataModel = null;
			xEvaluationResultWriter.flush();
			xEvaluationResultWriter.close();
			m_logger.info("Enter the person name who has to be searched for. XXX to stop searching");
			xSearchTerm = scanner.next();
		}
		scanner.close();
	}

	
	private List<QuerySolution> queryModel(String xSearchTerm, String p_dataFile) {
		m_DataModel = RDFDataMgr.loadModel(p_dataFile);
		List<QuerySolution> xQuerySolList = QueryExecutor.executeQuery(
				GenericQueryMaker.getAllMatches(xSearchTerm), m_DataModel);
		/*
		 * m_DataModel = null; System.gc();
		 */
		return xQuerySolList;

	}

	/**
	 * Run the queries on person data.
	 * @param xEvaluationResultWriter
	 * @param xSearchTerm
	 */
	private void queryPersonData(PrintWriter xEvaluationResultWriter,
			String xSearchTerm) {
		List<QuerySolution> querySol = queryModel(xSearchTerm,
				m_Configurator.get_person_data_file());
		if (querySol.size() > 0)
			xEvaluationResultWriter.println("Entity type is Person");

		m_logger.info("Entity type of person");
		QueryExecutor.executeQueryAsString(PersonQueryMaker.getEntityType(
				xSearchTerm, NounClassifierConstants.LANG_EN), m_DataModel);

		m_logger.info("Description of person");
		QueryExecutor.executeQueryAsString(PersonQueryMaker.getDescription(
				xSearchTerm, NounClassifierConstants.LANG_EN), m_DataModel);

		m_logger.info("Birth Place of person");
		QueryExecutor.executeQueryAsString(PersonQueryMaker.getBirthPlace(
				xSearchTerm, NounClassifierConstants.LANG_EN), m_DataModel);

		m_logger.info("Death Place type of person");
		QueryExecutor.executeQueryAsString(PersonQueryMaker.getDeathPlace(
				xSearchTerm, NounClassifierConstants.LANG_EN), m_DataModel);
	}

	
	/**
	 * Search whether the given location is present in location data or not.
	 * @param xEvaluationResultWriter
	 * @param xSearchTerm
	 */
	private void queryLocationData(PrintWriter xEvaluationResultWriter,
			String xSearchTerm) {
		List<QuerySolution> querySol = queryModel(xSearchTerm,
				m_Configurator.get_location_data_file());
		if (querySol.size() > 0)
			xEvaluationResultWriter.println("Entity type is location");
		
	}
	
	/** 
	 * Search the ambiguity terms for the given search term.
	 * @param xEvaluationResultWriter
	 * @param xSearchTerm
	 */
	private void queryDisamgData(PrintWriter xEvaluationResultWriter,
			String xSearchTerm) {
		List<QuerySolution> querySol = queryModel(xSearchTerm,
				m_Configurator.get_disambiguation_data_file());
		Iterator<QuerySolution> itr = querySol.iterator();
		while (itr.hasNext()) {
			String s = itr.next().toString();
			xEvaluationResultWriter.println(s.substring(
					s.lastIndexOf("/") + 1, s.lastIndexOf(">")));
		}
		
	}
	
}
