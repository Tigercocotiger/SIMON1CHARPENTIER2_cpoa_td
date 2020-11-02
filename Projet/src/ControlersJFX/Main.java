package ControlersJFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage Stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/FXML/accueil.fxml"));
			Scene scene = new Scene(root);
			Stage.setResizable(false);
			Stage.setTitle("ACCEUIL");
			Stage.setScene(scene);
			Stage.setTitle("Accueil");
			Stage.getIcons().add(new Image("/images/pull.png"));
			Stage.centerOnScreen();
			Stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}