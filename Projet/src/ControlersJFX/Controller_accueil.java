package ControlersJFX;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller_accueil implements Initializable {

	public void swapproduit(ActionEvent event) throws Exception{
		System.out.println("yo");
		Parent tablViewParent = FXMLLoader.load(getClass().getResource("/FXML/produit.fxml"));
		Scene tablViewsScene = new Scene(tablViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tablViewsScene);
		window.setTitle("Produit");
		window.show();
		
	}

	public void swapcateg(ActionEvent event) throws Exception{
		System.out.println("yo");
		Parent tablViewParent = FXMLLoader.load(getClass().getResource("/FXML/categ.fxml"));
		Scene tablViewsScene = new Scene(tablViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tablViewsScene);
		window.setTitle("Catégorie");
		window.show();
	}
	
	public void swapclient(ActionEvent event) throws Exception{
		System.out.println("yo");
		Parent tablViewParent2 = FXMLLoader.load(getClass().getResource("/FXML/client.fxml"));
		Scene tablViewsScene = new Scene(tablViewParent2);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tablViewsScene);
		window.setTitle("Client");
		window.centerOnScreen();
		window.show();
	
	}
	
	public void swapcommande(ActionEvent event) throws Exception{
		System.out.println("yo");
		Parent tablViewParent = FXMLLoader.load(getClass().getResource("/FXML/commande.fxml"));
		Scene tablViewsScene = new Scene(tablViewParent);
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(tablViewsScene);
		window.setTitle("Commande");
		window.show();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}
