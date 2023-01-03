package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import sae.Etablissement;
import sae.Etudiant;
import sae.Matiere;
import sae.Tuteur;
import sae.Tutore;

class EtablissementTest {

	@Test
	void testAddTuteur() {
		Tuteur t = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Etudiant e = new Etudiant("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Etablissement iut = new Etablissement();
		iut.addTuteur(e);
		assertTrue(iut.getTuteur().contains(t));
	}
	@Test
	void testAddTutore() {
		Tutore t = new Tutore("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Etudiant e = new Etudiant("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Etablissement iut = new Etablissement();
		iut.addTutore(e);
		assertTrue(iut.getTutore().contains(t));
	}
	@Test
	void testEtuParMatiere() {
		Etablissement iut = new Etablissement();
		Tuteur t = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Tuteur t2 = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.DEVOO);
		iut.addEtudiant(t);
		iut.addEtudiant(t2);
		iut.etuParMatiere(Matiere.MATHS);
		for(Etudiant e : iut.getTuteur()) {
			assertEquals(e.getMatiere(),Matiere.MATHS);
		}
	}
	@Test
	void testSplit() {
		Etablissement iut = new Etablissement();
		Tuteur t = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Tutore t2 = new Tutore ("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.DEVOO);
		iut.addEtudiant(t2);
		iut.addEtudiant(t);
		iut.split();
		ArrayList<Tuteur> tuteur = new ArrayList<Tuteur>();
		ArrayList<Tutore> tutore = new ArrayList<Tutore>();
		tuteur.add(t);
		tutore.add(t2);
		assertEquals(iut.getTuteur(),tuteur);
		assertEquals(iut.getTutore(),tutore);
	}
	@Test
	void testBalanceLists() {
		Etablissement iut = new Etablissement();
		Tuteur t = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS);
		Tuteur t2 = new Tuteur("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.DEVOO);
		Tutore t3 = new Tutore ("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.DEVOO);
		iut.addTuteur(t);
		iut.addTuteur(t2);
		iut.addTutore(t3);
		iut.balanceLists();
		assertEquals(iut.getTuteur().size(), iut.getTutore().size());
	}
}










