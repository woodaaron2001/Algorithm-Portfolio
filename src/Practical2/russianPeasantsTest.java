package Practical2;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

public class russianPeasantsTest {

	@Test
	void testPositive() {
		assertEquals(16,russianPeasants.peasantUser(4, 4));
	}
	
	@Test
	void testNegative() {
		assertEquals(-16,russianPeasants.peasantUser(4, -4));
	}
	
	@Test
	void testBigIntIterative() {
		assertEquals(BigInteger.valueOf(16),russianPeasants.peasantIt(BigInteger.valueOf(4), BigInteger.valueOf(4)));
	}
	
	
	@Test
	void testBigIntRecursive() {
		assertEquals(BigInteger.valueOf(16),russianPeasants.peasantrecur(BigInteger.valueOf(4), BigInteger.valueOf(4)));
	}

}
