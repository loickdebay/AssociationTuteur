package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sae.Matiere;
import sae.Tuteur;

class TuteurTest {

	@Test
	void testpoidsTuteur () {
		Tuteur t = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		assertEquals(t.getPoids(), (10.34-5+1+1));
	}
	@Test
	void testcompareTo() {
		Tuteur t = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Tuteur t2 = new Tuteur("Louvier", "Gautier", 1, 5, 1, 13.34, Matiere.MATHS);
		assertEquals(t.compareTo(t2), -1);
		assertEquals(t.compareTo(t), 0);
		assertEquals(t2.compareTo(t), 1);
	}
	

}
