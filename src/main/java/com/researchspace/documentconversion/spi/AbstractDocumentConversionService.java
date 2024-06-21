package com.researchspace.documentconversion.spi;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Abstract implementation with some helper methods
 */
public abstract class AbstractDocumentConversionService {

	 static final String DEFAULT_MIMETYPE = "application/octet-stream";

	/**
	 * Removes the 'file' part of the URI protocol from non-Windows machines,
	 * resulting in an absolute file path.
	 * 
	 * @param toConvert
	 *            The {@link Convertible}
	 * @return the absolute filepath without the 'file:' prefix for non-Windows
	 *         machines
	 */
	protected String getFilePathFromURI(Convertible toConvert) {
		try {
			return new File(new URI(toConvert.getFileUri())).getAbsolutePath();
		} catch (URISyntaxException e) {
			return null;
		}
	}

	/**
	 * Gets a mimetype for a given output extension
	 * 
	 * @return a Mime type. Default is <code>application/octet-stream</code>
	 */
	protected String getContentTypeForExtension(String outputExtension) {
		if (outputExtension == null) {
			return DEFAULT_MIMETYPE;
		}
		switch (outputExtension.toLowerCase()) {
			case "pdf": {
				return "application/pdf";
			}
			case "png": {
				return "image/png";
			}
			case "html": {
				return "text/html";
			}
			default: {
				return DEFAULT_MIMETYPE;
			}
		}
	}

}
