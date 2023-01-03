package IHM;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import sae.Matiere;

public class FourthWindowController implements Initializable{
	@FXML
	protected void confirmer(ActionEvent e) throws IOException {
		new SecondWindow(matiereselect.getValue());
	}
	@FXML
	private ComboBox<Matiere> matiereselect;
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		matiereselect.getItems().addAll(Matiere.values());
	}
}
