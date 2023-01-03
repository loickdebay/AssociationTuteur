package IHM;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainIHM extends Application {

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws IOException {
		new FirstWindow();
	}
}
