package edu.louisiana.cacs.nounclassifier;

import edu.louisiana.cacs.nounclassifier.exception.NounClassifierException;

public interface INounClassifier {

	public void classify() throws NounClassifierException;
}
