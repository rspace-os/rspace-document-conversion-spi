package com.researchspace.documentconversion.spi;

import java.io.File;
/**
 * Simple adapator class to adapt <code>java.io.File</code> objects
 * to {@link Convertible} interface.
 */
public class ConvertibleFile implements Convertible {


	private File toConvert;
	/**
	 * @param toConvert The file to be converted
	 */
	public ConvertibleFile(File toConvert) {
		super();
		this.toConvert = toConvert;
	}

	
	/**
	 * Gets the underlying filename
	 */
	@Override
	public String getName() {
		return toConvert.getName();
	}

	/**
	 * Gets the underlying file URI
	 */
	@Override
	public String getFileUri() {
		return toConvert.toURI().toString();
	}
	
	@Override
	public String toString() {
		return "ConvertibleFile [toConvert=" + toConvert + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((toConvert == null) ? 0 : toConvert.hashCode());
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
		ConvertibleFile other = (ConvertibleFile) obj;
		if (toConvert == null) {
			if (other.toConvert != null)
				return false;
		} else if (!toConvert.equals(other.toConvert))
			return false;
		return true;
	}
}
