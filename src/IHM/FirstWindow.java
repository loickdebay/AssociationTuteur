package IHM;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstWindow extends Stage {

	public FirstWindow() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("sceneprincipale.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		this.setScene(scene);
		this.setTitle("Fenetre principale");
		this.show();
	}
}