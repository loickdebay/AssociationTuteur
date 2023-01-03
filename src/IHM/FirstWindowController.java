package IHM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import fr.ulille.but.sae2_02.graphes.CalculAffectation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sae.Etablissement;
import sae.Etudiant;
import sae.FileReading;
import sae.Graphe;
import sae.Matiere;
import sae.Tuteur;
import sae.Tutore;

public class FirstWindowController implements Initializable {

	@FXML
	protected void generer(ActionEvent e) throws IOException {
		new FourthWindow();
	}

	@FXML
	private TableView<Etudiant> etudiants;
	@FXML
	private TableColumn<Etudiant, String> nom;
	@FXML
	private TableColumn<Etudiant, String> prenom;
	@FXML
	private TableColumn<Etudiant, Integer> annee;
	@FXML
	private TableColumn<Etudiant, Integer> motivation;
	@FXML
	private TableColumn<Etudiant, Integer> moyenne;
	@FXML
	private TableColumn<Etudiant, Integer> abs;
	@FXML
	private TableColumn<Etudiant, Matiere> matiere;
	@FXML
	private TextField textnom;
	@FXML
	private TextField textprenom;
	@FXML
	private TextField textannee;
	@FXML
	private TextField textmotivation;
	@FXML
	private TextField textmoyenne;
	@FXML
	private TextField textAbs;
	@FXML
	private ComboBox<Matiere> textmatiere;

	@FXML
	protected void add(ActionEvent e) throws IOException {
		if (!textnom.getText().trim().isEmpty() || !textprenom.getText().trim().isEmpty()
				|| !textannee.getText().trim().isEmpty() || !textmotivation.getText().trim().isEmpty()
				|| !textmoyenne.getText().trim().isEmpty() || !textAbs.getText().trim().isEmpty()
				|| !textmatiere.getSelectionModel().isEmpty()) {
			try {
				if (0 < Integer.parseInt(textannee.getText()) && Integer.parseInt(textannee.getText()) < 4) {
					if (0 < Integer.parseInt(textmotivation.getText())
							&& Integer.parseInt(textmotivation.getText()) < 11) {
						if (0.0 < Double.parseDouble(textmoyenne.getText())
								&& Double.parseDouble(textmoyenne.getText()) <= 20.0) {
							if (0 <= Integer.parseInt(textAbs.getText()) && Integer.parseInt(textAbs.getText()) < 300) {
								Etudiant nouveau = new Etudiant(textnom.getText(), textprenom.getText(),
										Integer.parseInt(textannee.getText()),
										Integer.parseInt(textmotivation.getText()), Integer.parseInt(textAbs.getText()),
										Double.parseDouble(textmoyenne.getText()), textmatiere.getValue());
								ArrayList<Etudiant> etu = FileReading.readJson();
								etu.add(nouveau);
								FileReading.createJson(etu);
								etudiants.getItems().clear();
								etudiants.setItems(getEtu());
								textnom.clear();
								textprenom.clear();
								textannee.clear();
								textmotivation.clear();
								textmoyenne.clear();
								textAbs.clear();
								
							}
						}
					}
				}
			} catch (NumberFormatException e2) {
				System.out.println("exeption");
			}
		}
	}

	@FXML
	protected void supp(ActionEvent e) throws IOException {
		ArrayList<Etudiant> etu = FileReading.readJson();
		Etudiant selectedItem = etudiants.getSelectionModel().getSelectedItem();
		if (selectedItem.equals(null)) {
		} else {
			etu.remove(selectedItem);
			FileReading.createJson(etu);
			etudiants.getItems().clear();
			etudiants.setItems(getEtu());
		}
	}
	@FXML
	protected void cree(ActionEvent e) throws IOException {
		Etablissement iut = new Etablissement();
		ArrayList<Etudiant> etu = new ArrayList<Etudiant>();
		for(Etudiant t : etudiants.getSelectionModel().getSelectedItems()) {
			etu.add(t);
		}
		iut.sortEtudiants(etu);
		iut.triTuteur();
		iut.triTutore();
		iut.split();
		iut.balanceLists();
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
    	CalculAffectation<String> ca = new CalculAffectation<>(etus.getGraphe(),tuteurString,tutoreString);
    	FileReading.saveAffectation(ca);
    	
    	String contentfinal = Files.readString(Paths.get(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"final.json"));
    	String contentaff = Files.readString(Paths.get(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"affectations.json"));
    	if(contentfinal.length()>1) {
    		contentfinal=contentfinal.substring(0, contentfinal.length()-1);
    		contentfinal=contentfinal+",";
    		contentaff=contentaff.substring(1, contentaff.length());
    	}
    	
		BufferedWriter writer = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+File.separator+"ressources"+File.separator+"final.json"));
	    writer.write(contentfinal+ contentaff);
	    writer.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(
					System.getProperty("user.dir") + File.separator + "ressources" + File.separator + "final.json"));
			writer.write("");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		nom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
		prenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
		annee.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("annee"));
		motivation.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("motivation"));
		moyenne.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("moyenne"));
		abs.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("nbAbs"));
		matiere.setCellValueFactory(new PropertyValueFactory<Etudiant, Matiere>("matiere"));
		
		textmatiere.getItems().addAll(Matiere.values());
		
		try {
			etudiants.setItems(getEtu());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		etudiants.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	}

	public ObservableList<Etudiant> getEtu() throws IOException {
		ObservableList<Etudiant> Etu = FXCollections.observableArrayList();
		Etu.addAll(FileReading.readJson());
		return Etu;
	}
}
