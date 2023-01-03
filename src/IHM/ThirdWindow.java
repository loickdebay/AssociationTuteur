package IHM;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ThirdWindow extends Stage{
	
	public ThirdWindow() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("sceneseuls.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		this.setScene(scene);
		this.setTitle("Etudiants sans groupes");
		this.show();
	}
}