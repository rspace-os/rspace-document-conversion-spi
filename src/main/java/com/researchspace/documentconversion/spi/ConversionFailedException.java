package com.researchspace.documentconversion.spi;

/**
 * Runtime exception that wraps underlying exceptions in the document conversion implementation
 */
public class ConversionFailedException extends RuntimeException {

	public ConversionFailedException (String message, Exception cause) {
		super(message,cause);
	}

}
