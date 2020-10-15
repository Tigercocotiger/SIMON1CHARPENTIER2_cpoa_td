package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class Controller {
	public Label LblInfo;
	public TextField TfNom;
	public TextArea TfDesc;
	public TextField TfTarif;
	public ChoiceBox<String> ComboB;
	@FXML
    private void printResultat(ActionEvent event) {
        event.consume();
        String LblNom = TfNom.getText().trim();
        String LblDesc = TfDesc.getText().trim();
        String LblTarif = TfTarif.getText().trim();
        String value = (String) ComboB.getValue();
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
	}