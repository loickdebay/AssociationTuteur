package IHM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import fr.ulille.but.sae2_02.graphes.Arete;
import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sae.Etablissement;
import sae.Etudiant;
import sae.FileReading;
import sae.Graphe;
import sae.Tuteur;
import sae.Tutore;

public class SecondWindowController implements Initializable {
	@FXML
	protected void affiche(ActionEvent e) throws IOException {
		new ThirdWindow();
	}
	@FXML
	private TableView<Arete> couple;
	@FXML
	private TableColumn<Tuteur, String> nomte;
	@FXML
	private TableColumn<Tutore, String> nomto;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomte.setCellValueFactory(new PropertyValueFactory<Tuteur, String>("extremite1"));
		nomto.setCellValueFactory(new PropertyValueFactory<Tutore, String>("extremite2"));
				
		Etablissement iut = new Etablissement();
		ArrayList<Etudiant> etu = null;
		try {
			etu = FileReading.readJson();
			Reader reader = Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"final.json"));
			Reader readertest = Files.newBufferedReader(Paths.get(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"final.json"));
			if(readertest.read()!=-1) {
				Arete[] aff = new Gson().fromJson(reader,Arete[].class);
				ArrayList<Arete> etuList = new ArrayList<Arete>(Arrays.asList(aff));
				ArrayList<Etudiant> etusupp = new ArrayList<Etudiant>();
				for (int i = 0; i < etuList.size(); i++) {
					for(Etudiant t : etu) {
						if(t.getNom().equals(etuList.get(i).getExtremite1())||t.getNom().equals(etuList.get(i).getExtremite2())) {
							etusupp.add(t);
						}
					}
				}
				etu.removeAll(etusupp);
			}
			reader.close();
			readertest.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		iut.sortEtudiants(etu);
		iut.triTuteur();
		iut.triTutore();
		iut.etuParMatiere(SecondWindow.matiereselected);
		iut.split();
		iut.balanceLists();
		Graphe etus = new Graphe();
		etus.ajouterSommets(iut.getTuteur(), iut.getTutore());
		etus.ajouterAretes(iut.getTuteur(), iut.getTutore());
		ArrayList<String> tuteurString = new ArrayList<String>();
		ArrayList<String> tutoreString = new ArrayList<String>();
		for (Tuteur t : iut.getTuteur()) {
			tuteurString.add(t.getNom());
		}
		for (Tutore t : iut.getTutore()) {
			tutoreString.add(t.getNom());
		}
		CalculAffectation<String> ca = new CalculAffectation<>(etus.getGraphe(), tuteurString, tutoreString);
		try {
			FileReading.saveAffectation(ca);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String contentfinal = null;
		String contentaff = null;
		try {
			contentfinal = Files.readString(Paths.get(
					System.getProperty("user.dir") + File.separator + "ressources" + File.separator + "final.json"));
			contentaff = Files.readString(Paths.get(System.getProperty("user.dir") + File.separator + "ressources"
					+ File.separator + "affectations.json"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (contentfinal.length() > 10) {
			contentfinal = contentfinal.substring(0, contentfinal.length() - 1);
			contentfinal = contentfinal + ",";
			contentaff = contentaff.substring(1, contentaff.length());
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(
					System.getProperty("user.dir") + File.separator + "ressources" + File.separator + "final.json"));
			writer.write(contentfinal + contentaff);
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			couple.setItems(getcouple());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ObservableList<Arete> getcouple() throws IOException {
		ObservableList<Arete> Etu = FXCollections.observableArrayList();
		Reader reader = Files.newBufferedReader(Paths
				.get(System.getProperty("user.dir") + File.separator + "ressources" + File.separator + "final.json"));
		Arete[] aff = new Gson().fromJson(reader, Arete[].class);
		ArrayList<Arete> couples = new ArrayList<Arete>(Arrays.asList(aff));
		reader.close();
		Etu.addAll(couples);
		return Etu;
	}

}
