package sae;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
/**
 * classe gerant la lecture/ecriture de fichiers externes
 */
public class FileReading {
	/**
	 * Sauvegarde une liste d'etudiants dans un fichier etudiants.json
	 * @param etu la liste a sauvegarder
	 * @throws IOException exception
	 */
	public static void createJson (ArrayList<Etudiant> etu) throws IOException {
		Gson gson = new Gson();
		FileWriter fw = new FileWriter(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"etudiants.json");
		gson.toJson(etu,fw);
		fw.close();
	}
	/**
	 * Lit un fichier Json contenant des etudiants et en retourne une liste d'etudiants
	 * @return la liste d'etudiants lue
	 * @throws IOException exception
	 */
	public static ArrayList<Etudiant> readJson() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"etudiants.json"));
		Etudiant[] etu = new Gson().fromJson(reader,Etudiant[].class);
		ArrayList<Etudiant> etuList = new ArrayList<Etudiant>(Arrays.asList(etu));
		reader.close();
		return etuList;
	}
	/**
	 * Lit un fichier Json content les Affectations generees et en retourne une liste d'aretes
	 * @return la liste d'aretes lue
	 * @throws IOException exception
	 */
	@SuppressWarnings("rawtypes")
	public
	static ArrayList<Arete> readAffectation() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"affectations.json"));
		Arete[] aff = new Gson().fromJson(reader,Arete[].class);
		ArrayList<Arete> etuList = new ArrayList<Arete>(Arrays.asList(aff));
		reader.close();
		return etuList;
	}
	/**
	 * Sauvegarde un calcul d'affectation dans un fichier affectations.json
	 * @param ca le calcul d'affectation
	 * @throws IOException exception
	 */
	public static void saveAffectation(CalculAffectation<String> ca) throws IOException{
		Gson gson = new Gson();
		FileWriter fw = new FileWriter(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"affectations.json");
		gson.toJson(ca.getAffectation(),fw);
		fw.close();
	}
}
