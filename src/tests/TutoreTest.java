package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sae.Matiere;
import sae.Tutore;

class TutoreTest {

	@Test
	void testpoidsTutore () {
		Tutore t = new Tutore("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		assertEquals(t.getPoids(), (10.34-5+1+1));
	}
	@Test
	void testcompareTo() {
		Tutore t = new Tutore("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Tutore t2 = new Tutore("Louvier", "Gautier", 1, 5, 1, 13.34, Matiere.MATHS);
		assertEquals(t.compareTo(t2), -1);
		assertEquals(t.compareTo(t), 0);
		assertEquals(t2.compareTo(t), 1);
		
	}

}
