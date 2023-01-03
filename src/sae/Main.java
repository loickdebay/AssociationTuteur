package sae;


import java.io.IOException;
import java.util.ArrayList;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
/**
 * permet de lancer un scenario du projet
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		Etablissement iut = new Etablissement();
		
		ArrayList<Etudiant> listejson = new ArrayList<Etudiant>();
		System.out.println("Creation d'une liste d'etudiants a ajouter au fichier Json");
    	System.out.println();
		listejson.add(new Etudiant("Louvier", "Gautier", 1, 5, 1, 10.34, Matiere.MATHS));
		listejson.add(new Etudiant("Dupont", "Matteo", 1, 1, 2, 7.6, Matiere.MATHS));
		listejson.add(new Etudiant("omasson", "gab", 1, 2, 4, 11.5, Matiere.DEVOO));
		listejson.add(new Etudiant("locmant", "maxence", 1, 15, 0, 10, Matiere.DEVOO));
		listejson.add(new Etudiant("demand", "cyril", 2, 0, 5, 17, Matiere.MATHS));
		listejson.add(new Etudiant("delcroix", "noe", 2, 1, 2, 16, Matiere.MATHS));
		listejson.add(new Etudiant("Luma", "robin", 2, 2, 4, 14, Matiere.MATHS));
		listejson.add(new Etudiant("Debay", "Loick", 2, 4, 1, 11, Matiere.DEVOO));
		listejson.add(new Etudiant("dellis", "Timothe", 3, 6, 1, 11.6, Matiere.RESEAU));
		System.out.println("Liste a ajouter : "+listejson);
		System.out.println("Creation du json");
		FileReading.createJson(listejson);
		System.out.println("succes");
		System.out.println("Lecture du json");
		ArrayList<Etudiant> etu = FileReading.readJson();
    	System.out.println(FileReading.readJson());
    	System.out.println();
		System.out.println("Tri et equilibrage des etudiants par role et matieres (ici Maths)");
		iut.sortEtudiants(etu);
		/**
		 * Trie les etudiants par roles, puis uniquement ceux interesses par les maths, puis equilibre les listes pour qu'elles soient de la meme taille
		 */
		iut.triTuteur();
		iut.triTutore();
		iut.etuParMatiere(Matiere.DEVOO);
        iut.split();
		iut.balanceLists();
    	System.out.println();
    	System.out.println("Liste des Tuteurs et Tutores");
        System.out.println("Tuteurs : "+ iut.getTuteur());
        System.out.println("Tutores : "+ iut.getTutore());
    	System.out.println();
        /**
         * Calcule l'affectation en ajoutant tout les etudiants obtenus precedemment
         */
        Graphe etus = new Graphe();
    	etus.ajouterSommets(iut.getTuteur(), iut.getTutore());
    	etus.ajouterAretes(iut.getTuteur(), iut.getTutore());
    	ArrayList<String> tuteurString = new ArrayList<String>();
    	ArrayList<String> tutoreString = new ArrayList<String>();
    	for(Tuteur t : iut.getTuteur()) {
    		tuteurString.add(t.getNom());
    	}
    	for(Tutore t : iut.getTutore()) {
    		tutoreString.add(t.getNom());
    	}
    	System.out.println("Calcul de l'affectation");
    	System.out.println();
    	CalculAffectation<String> ca = new CalculAffectation<>(etus.getGraphe(),tuteurString,tutoreString);
    	System.out.println("Affectation obtenue : "+ca.getAffectation());
    	System.out.println("Sauvegarde dans le json affectations");
    	FileReading.saveAffectation(ca);
    	System.out.println("Succes");
    	System.out.println("Lecture du fichier Json d'affectations");	
		System.out.println(FileReading.readAffectation());
	}
}
