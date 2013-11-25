package edu.louisiana.cacs.nounclassifier.exception;

/**
 * 
 * Custom exception class for Noun Classifier operations
 * @author rsunkara
 *
 */
public class NounClassifierException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6512440342012272784L;
	
	public NounClassifierException(String errorMessage){
		super(errorMessage);
	}

}
