package Practical9;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CompressionTest {

	@Test
	void runLengthTest() {
		assertEquals("a4b3c2d5",DataCompression.runLengthEncoding("aaaabbbccddddd"));
	}

}
