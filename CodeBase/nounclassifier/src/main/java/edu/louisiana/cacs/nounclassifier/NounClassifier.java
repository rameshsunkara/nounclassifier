package edu.louisiana.cacs.nounclassifier;

import java.util.Scanner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.riot.RDFDataMgr;

import com.hp.hpl.jena.rdf.model.Model;

import edu.louisiana.cacs.nounclassifier.dao.QueryExecutor;
import edu.louisiana.cacs.nounclassifier.exception.NounClassifierException;
import edu.louisiana.cacs.nounclassifier.queries.PersonQueryMaker;
import edu.louisiana.cacs.nounclassifier.util.Configurator;
import edu.louisiana.cacs.nounclassifier.util.NounClassifierConstants;

public class NounClassifier implements INounClassifier {

	private static Log m_logger = LogFactory.getLog(NounClassifier.class);

	private Configurator m_Configurator = null;

	private Model m_dataModel = null;

	public NounClassifier(Configurator p_Configurator) {
		m_Configurator = p_Configurator;
	}

	public void execute() throws NounClassifierException {
		m_logger.trace("Enter execute()");

		m_dataModel = RDFDataMgr.loadModel(m_Configurator
				.get_person_data_file());
		m_logger.trace("Data loaded to model");
		Scanner scanner = new Scanner(System.in);
		String xPersonName = "";
		m_logger.info("Enter the person name who has to be searched for. XXX to stop searching");
		xPersonName = scanner.next();
		while (!xPersonName.equalsIgnoreCase("xxx")) {
			QueryExecutor.executeQuery(PersonQueryMaker.getEntityType(
					xPersonName, NounClassifierConstants.LANG_EN), m_dataModel);
			QueryExecutor.executeQuery(PersonQueryMaker.getBirthPlace(
					xPersonName, NounClassifierConstants.LANG_EN), m_dataModel);
			QueryExecutor.executeQuery(PersonQueryMaker.getDeathPlace(
					xPersonName, NounClassifierConstants.LANG_EN), m_dataModel);
			QueryExecutor.executeQuery(PersonQueryMaker.getDescription(
					xPersonName, NounClassifierConstants.LANG_EN), m_dataModel);
			m_logger.info("Enter the person name who has to be searched for. XXX to stop searching");
			xPersonName = scanner.next();
		}
		scanner.close();
	}

}
