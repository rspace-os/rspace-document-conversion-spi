package com.researchspace.documentconversion.spi;

import java.io.File;

/**
 * POJO class containing the File of the converted document, and its content
 * type. This should be immutable as it encapsulates the result of the
 * conversion operation.
 */
public class ConversionResult {

	private final File converted;
	private final String contentType;
	private final boolean successful;
	private final String errorMsg;

	public boolean isSuccessful() {
		return successful;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public File getConverted() {
		return converted;
	}

	public String getContentType() {
		return contentType;
	}

	/**
	 * If successful, <code>errMessage</code> can be <code>null</code>.<br/>
	 * If failed, then <code>converted</code>, <code>contentType</code> can be
	 * <code>null</code> but <code>errMessage</code> should be non-null (and 
	 *  <code>succeeded</code> should be <code>false</code>).
	 * 
	 * @param converted The converted file 
	 * @param contentType mimetype
	 * @param succeeded boolean
	 * @param errMessage the error message
	 */
	public ConversionResult(File converted, String contentType, boolean succeeded, String errMessage) {
		super();
		this.converted = converted;
		this.contentType = contentType;
		this.successful = succeeded;
		this.errorMsg = errMessage;
	}

	/**
	 * ConvenienceFor a successful conversion
	 * @param converted the converted file
	 * @param contentType mimeType of converted file
	 */
	public ConversionResult(File converted, String contentType) {
		this(converted, contentType, true, null);
	}

	/**
	 * Constructor for failed conversion
	 * @param errMessage  the error message
	 */
	public ConversionResult(String errMessage) {
		this(null, null, false, errMessage);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((converted == null) ? 0 : converted.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConversionResult other = (ConversionResult) obj;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (converted == null) {
			if (other.converted != null)
				return false;
		} else if (!converted.equals(other.converted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConversionResult [converted=" + converted + ", contentType=" + contentType + ", successful="
				+ successful + ", errorMsg=" + errorMsg + "]";
	}
}
