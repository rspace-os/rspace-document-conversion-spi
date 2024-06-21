package com.researchspace.documentconversion.spi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class NullServiceTest {

	@Test
	public void exceptionAlwaysThrown() {
		assertThrows(UnsupportedOperationException.class, ()->DocumentConversionService.NULL_SERVICE.convert(null, null));
	}
	@Test
	public void canConvertFalse() {
		assertFalse(DocumentConversionService.NULL_SERVICE.supportsConversion(new ConvertibleFile(null), "any"));
	}

}
