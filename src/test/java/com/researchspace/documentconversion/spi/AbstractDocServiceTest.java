package com.researchspace.documentconversion.spi;

import static com.researchspace.documentconversion.spi.AbstractDocumentConversionService.DEFAULT_MIMETYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AbstractDocServiceTest {
	
	class TestConverter extends AbstractDocumentConversionService {
		
	}
	// need double quotes so as to get literal ' characters put in output
	@ParameterizedTest(name = "{index} => extension=''{1}'', mimeType=''{0}''")
	@MethodSource("contentTypeProvider")
	public void testMimeTypeForExtension(String expectedMimeType, String extension) {
		assertEquals(expectedMimeType, new TestConverter().getContentTypeForExtension(extension));
	}
	
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> contentTypeProvider() {
		    //arg  0 is expected, arg1 is test value
	        return Stream.of(
	                Arguments.of(DEFAULT_MIMETYPE, "unknown"),
	                Arguments.of(DEFAULT_MIMETYPE, null),
	                Arguments.of(DEFAULT_MIMETYPE, ""),
	                Arguments.of("image/png", "PNG"),
	                Arguments.of("image/png", "png"),
	                Arguments.of("application/pdf", "pdf"),
	                Arguments.of("text/html", "html")
	        );
	    }

}
