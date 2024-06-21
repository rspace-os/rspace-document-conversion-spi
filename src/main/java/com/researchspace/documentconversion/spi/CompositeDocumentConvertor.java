package com.researchspace.documentconversion.spi;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.researchspace.documentconversion.spi.ConversionResult;
import com.researchspace.documentconversion.spi.Convertible;
import com.researchspace.documentconversion.spi.DocumentConversionService;

/**
 * Delegates to  a list of converters. Will use the first converter capable of supporting the conversion.
 */
public class CompositeDocumentConvertor implements DocumentConversionService {

	public void setDelegates(List<DocumentConversionService> delegates) {
		this.delegates = delegates;
	}

	private List<DocumentConversionService> delegates = new ArrayList<>();
	@Override
	public ConversionResult convert(Convertible toConvert, String outputExtension) {
		for (DocumentConversionService delegate: delegates){
			if (delegate.supportsConversion(toConvert, outputExtension)){
				return delegate.convert(toConvert, outputExtension);
			}
		}
		String msg = getFailureMsg(toConvert, outputExtension);
		throw new UnsupportedOperationException(msg);	
	}
	
	@Override
	public ConversionResult convert(Convertible toConvert, String outputExtension, File outfile) {
		for (DocumentConversionService delegate: delegates){
			if (delegate.supportsConversion(toConvert, outputExtension)){
				return delegate.convert(toConvert, outputExtension, outfile);
			}
		}
		// only get here if unsupported
		String msg = getFailureMsg(toConvert, outputExtension);
		throw new UnsupportedOperationException(msg);	
	}

	private String getFailureMsg(Convertible toConvert, String outputExtension) {
		String msg = MessageFormat.format("There is no converter to convert {0} to {1} - please ask your administrator"
				+ " to set up a document converter to support  this conversion.",
				toConvert.getName(), outputExtension);
		return msg;
	}

	/*
	 */
	@Override
	public boolean supportsConversion(Convertible toConvert, String to) {
		for (DocumentConversionService delegate: delegates){
			if (delegate.supportsConversion(toConvert, to)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Gets a read-only list of the delegate {@link DocumentConversionService} collection
	 * @return delegate list
	 */
	public List<DocumentConversionService> getDelegates (){
		return Collections.unmodifiableList(delegates);
	}
}
