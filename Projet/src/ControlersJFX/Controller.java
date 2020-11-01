package ControlersJFX;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import metier.CMCategorie;
import sql.Connexion;
import sql.MySQLCategorieDAO;

public class Controller implements Initializable {
	@FXML
	public Label LblInfo;
	@FXML
	public TextField TfNom;
	@FXML
	public TextArea TfDesc;
	@FXML
	public TextField TfTarif;
	@FXML
	public ChoiceBox<CMCategorie> ComboB;
	@FXML
	public Button btn_supr;
	
	@FXML
    private void printResultat(ActionEvent event) {
        event.consume();
        String LblNom = TfNom.getText().trim();
        String LblDesc = TfDesc.getText().trim();
        String LblTarif = TfTarif.getText().trim();
        String value=null;
        
        if(ComboB.getSelectionModel().getSelectedIndex()!=-1)
        value = ComboB.getSelectionModel().getSelectedItem().getTitre();
        if (LblNom.isEmpty()|| LblDesc.isEmpty() || LblTarif.isEmpty()|| value == null){
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erreur lors de la saisie");
			alert.setHeaderText("Un ou plusieurs champs sont mal remplis.");
			alert.showAndWait();
		}
		else 
		{
			LblInfo.setText(LblNom + " " + LblDesc + " " + LblTarif + " " +value);
		}
        
        }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ComboB.setConverter(new StringConverter<CMCategorie>() {
            @Override
            public CMCategorie fromString(String nom) {
                return ComboB.getItems().stream().filter(categ -> categ.getTitre().equals(nom)).findFirst().orElse(null);
            }

            @Override
            public String toString(CMCategorie objet) {
                return objet.getTitre();
            }
        });
		try {
			ComboB.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	}