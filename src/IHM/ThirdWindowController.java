package IHM;

import java.io.File;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sae.Etablissement;
import sae.Etudiant;
import sae.FileReading;
import sae.Tuteur;
import sae.Tutore;

public class ThirdWindowController  implements Initializable{
	@FXML
	private TableView<Etudiant> seul;
	@FXML
	private TableColumn<Etudiant, String> nomseul;
	@FXML
	private TableColumn<Etudiant, String> prenomseul;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nomseul.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
		prenomseul.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));

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

		ObservableList<Etudiant> Etudiantseul = FXCollections.observableArrayList();
		Etudiantseul.addAll(etu);
		seul.setItems(Etudiantseul);
		
	}
	
}
