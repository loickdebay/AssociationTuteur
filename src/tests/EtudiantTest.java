package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sae.Etudiant;
import sae.Matiere;

class EtudiantTest {

	@Test
	void testpoidsEtudiant () {
		Etudiant e = new Etudiant("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		assertEquals(e.getPoids(), (16.66));
	}

}
