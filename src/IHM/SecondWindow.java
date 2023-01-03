package IHM;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sae.Matiere;

public class SecondWindow extends Stage {
	public static Matiere matiereselected;
	public SecondWindow(Matiere matiereselected) throws IOException {
		SecondWindow.matiereselected=matiereselected;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("scenecouples.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		this.setScene(scene);
		this.setTitle("Vue Des couples");
		this.show();
	}
}