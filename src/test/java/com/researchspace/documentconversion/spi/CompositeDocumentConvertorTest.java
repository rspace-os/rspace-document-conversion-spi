package com.researchspace.documentconversion.spi;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class CompositeDocumentConvertorTest {
	
	
	CompositeDocumentConvertor compositeConvertor;
	@Mock 
	private Convertible convertible;
	@Mock DocumentConversionService delegate1;
	@Mock DocumentConversionService delegate2;
	@Mock DocumentConversionService delegate3;

	
	private List<DocumentConversionService> delegateConverters;

	@BeforeEach
	public void setup (){
		// no mockito replacement for rule annotation in junit5, so have to call this instead
		MockitoAnnotations.initMocks(this);
		compositeConvertor = new CompositeDocumentConvertor();
		delegateConverters = Arrays.asList(new DocumentConversionService[]{delegate1, delegate2});
      
	}
	@Test
	public void handlesNoDelegatesGracefully() {
		assertFalse(compositeConvertor.supportsConversion(convertible, "unknown"));
		when(convertible.getName()).thenReturn("anyname");
		assertThrows(UnsupportedOperationException.class, ()->compositeConvertor.convert(convertible, "unknown"));
	}
	
	@Test
	@DisplayName("Gets 1st converter that matches")
	public void returnsFirstMatch() {
		when(convertible.getName()).thenReturn("anyname");
		when(convertible.getFileUri()).thenReturn("anyuri");
		// both return true
		for (DocumentConversionService delegate: delegateConverters) {
			when(delegate.supportsConversion(convertible, "any")).thenReturn(true);
		}
		compositeConvertor.setDelegates(delegateConverters);
		assertTrue(compositeConvertor.supportsConversion(convertible, "any"));
		//1st matches, 2nd not called.
		verify(delegate1, times(1)).supportsConversion(convertible, "any");
		verify(delegate2, never()).supportsConversion(convertible, "any");
	}
	
	@Test
	@DisplayName("returns false if no delegated converters match")
	public void handlesNoMatches() {
		when(convertible.getName()).thenReturn("anyname");
		when(convertible.getFileUri()).thenReturn("anyuri");
		// both return true
		for (DocumentConversionService delegate: delegateConverters) {
			when(delegate.supportsConversion(convertible, "any")).thenReturn(false);
		}
		compositeConvertor.setDelegates(delegateConverters);
		assertFalse(compositeConvertor.supportsConversion(convertible, "any"));
		//2nd  called.
		verify(delegate2, times(1)).supportsConversion(convertible, "any");
		// should throw UOE
		assertThrows(UnsupportedOperationException.class, ()->compositeConvertor.convert(convertible, "unknown"));
	}
	
	@Test
	public void assertgetDelegatesReturnsReadOnlyList() {
		compositeConvertor.setDelegates(delegateConverters);
		assertThrows(UnsupportedOperationException.class, ()->compositeConvertor.getDelegates().add(delegate3 ));
	}
	

}
