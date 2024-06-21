package com.researchspace.documentconversion.spi;

/**
 * Mixin interface that defines methods needed to get hold of a file whose format
 * can be converted into another format.
 */
public interface Convertible {
	/**
	 * The name of the document
	 * @return the name
	 */
	String getName();
	
	/**
	 * The file URI  from which the document to be converted can be retrieved.
	 * @return the URI
	 */
	String getFileUri();

}
