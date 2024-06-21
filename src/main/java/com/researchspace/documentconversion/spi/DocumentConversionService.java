package com.researchspace.documentconversion.spi;

import java.io.File;

/**
 * Converts media files from one format to another
 */
public interface DocumentConversionService {
	
	/**
	 * Generates a new representation of an {@link Convertible} from an existing source.
	 * The output file  can be retrieved fom the {@link ConversionResult}.
	 * @param toConvert An input  {@link Convertible}
	 * @param outputExtension the extension of the desired output format, e.g. 'pdf', 'html'
	 * @return a {@link ConversionResult} if conversion could be attempted.
	 * @throws UnsupportedOperationException if is not a supported conversion.
	 */
	ConversionResult convert(Convertible toConvert, String outputExtension);
	
	/**
	 * Generates a new representation of an {@link Convertible} from an existing source.
	 * The output file  can be retrieved fom the {@link ConversionResult}, and will be that
	 *  specified by the <code>outfile</code> argument
	 * @param toConvert An input  {@link Convertible}
	 * @param outputExtension the extension of the desired output format, e.g. 'pdf', 'html'
	 * @param outfile the location of the output file
	 * @return a {@link ConversionResult} if conversion could be attempted.
	 * @throws UnsupportedOperationException if is not a supported conversion.
	 */
	ConversionResult convert(Convertible toConvert, String outputExtension, File outfile);
	
	/**
	 * Boolean test for whether the conversion <code>from</code> to <code>to</code> is supported.
	 * @param toConvert - the {@link Convertible} object to be converted
	 * @param to - an extension of the desired format to convert <em>into</em> - 'html', 'pdf' etc
	 * @return <code>true</code> if is supported, <code>false</code> otherwise.
	 */
	boolean supportsConversion(Convertible toConvert, String to);
	
	/**
	 * A <code>no-op</code> service that returns <code>false</code> for supportConversion and throws
	 *  {@link UnsupportedOperationException} if any attempt is made to convert using this service.
	 */
	DocumentConversionService NULL_SERVICE = new DocumentConversionService (){

		@Override
		public ConversionResult convert(Convertible toConvert, String outputExtension) {
			return convert(toConvert, outputExtension, null);
		}

		@Override
		public ConversionResult convert(Convertible toConvert, String outputExtension, File outfile) {
			throw new UnsupportedOperationException("Conversion unsupported");
		}

		@Override
		public boolean supportsConversion(Convertible toConvert, String to) {
			return false;
		}
	};

}
