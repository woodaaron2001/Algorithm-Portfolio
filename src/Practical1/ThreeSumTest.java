package Practical1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ThreeSumTest {

	@Test
	void testCount() {
		int array[]  = {8,30,-30,-20,-10,40,0,10,15};
		assertEquals(4,ThreeSum.countA(array));
		assertEquals(4,ThreeSum.countB(array));
	}

}
