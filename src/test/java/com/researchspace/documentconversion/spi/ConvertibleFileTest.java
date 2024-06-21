package com.researchspace.documentconversion.spi;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class ConvertibleFileTest {

	File testFile =new File ("src/test/resources/RSblue1.png");
	@Test
	@DisplayName("EqualsHashcodeToString for ConvertibleFile")
	public void test() {
		ConvertibleFile cfile = new ConvertibleFile(testFile);
		ConvertibleFile cfile2 = new ConvertibleFile(testFile);
		assertEquals(cfile, cfile2);
		assertEquals(cfile.hashCode(), cfile2.hashCode());
		assertEquals(cfile.getName(),testFile.getName());
		assertTrue(cfile.toString().contains(testFile.getName()));
	}

}
