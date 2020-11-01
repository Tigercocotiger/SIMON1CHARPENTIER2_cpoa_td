package ControlersJFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import metier.CMCategorie;
import metier.CMProduit;
import sql.Connexion;

public class Controller_categ implements Initializable,ChangeListener<CMCategorie> {
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	@FXML
    private TableView<CMCategorie> table;
    @FXML
    private TextField tf_titre;

    @FXML
    private TextField tf_visuel;

    @FXML
    private TableColumn<CMCategorie,String> col_titre;

    @FXML
    private TableColumn<CMCategorie,String> col_visuel;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_suppr;
	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		table.setEditable(true);
		col_titre.setCellFactory(TextFieldTableCell.forTableColumn());
		col_visuel.setCellFactory(TextFieldTableCell.forTableColumn());
		this.table.getSelectionModel().selectedItemProperty().addListener(this);
		btn_add.setDisable(true);
		btn_suppr.setDisable(true);
		tabview();
	
	}
	
	
	public void modiftitre(CellEditEvent<?,?> edittedCell)
	{
	    CMCategorie categ =  table.getSelectionModel().getSelectedItem();
	    categ.setTitre(edittedCell.getNewValue().toString());
	    try {
			daos.getCategorieDAO().update(new CMCategorie(categ.getId(),categ.getTitre(),categ.getVisuel()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modifvisuel(CellEditEvent<?,?> edittedCell)
	{
	    CMCategorie categ =  table.getSelectionModel().getSelectedItem();
	    categ.setVisuel(edittedCell.getNewValue().toString());
	    try {
			daos.getCategorieDAO().update(new CMCategorie(categ.getId(),categ.getTitre(),categ.getVisuel()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void suppr(){
		btn_suppr.setDisable(true);
		CMCategorie selectedItem = table.getSelectionModel().getSelectedItem();
		try {
			daos.getCategorieDAO().delete(new CMCategorie(selectedItem.getId()));
			table.getItems().remove(selectedItem);
		} catch (Exception e) {				
			e.printStackTrace();
		}
		
		}
	public void add(ActionEvent event) throws IOException {
		String Lbltitre = tf_titre.getText().trim();
	    String Lblvisuel = tf_visuel.getText().trim();
		try {
			daos.getCategorieDAO().create(new CMCategorie(Lbltitre,Lblvisuel));
			table.getItems().addAll(new CMCategorie(Lbltitre,Lblvisuel));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tf_titre.clear();
		tf_visuel.clear();
		btn_add.setDisable(true);
	}
	public void test() {
		String Lbltitre = tf_titre.getText().trim();
	    String Lblvisuel = tf_visuel.getText().trim();
	    if (Lbltitre.isEmpty()|| Lblvisuel.isEmpty() ) {
	        	btn_add.setDisable(true);
	        }
	    else {
	    	btn_add.setDisable(false);
	    }
	}
	
	public void tabview() {
		int i =0,j=0;
		ObservableList<CMCategorie> list=  FXCollections.observableArrayList();
		table.getItems().clear();
		Connection cnx = Connexion.getInstance().getcon();
		try {
			ResultSet res= cnx.createStatement().executeQuery("Select * from Categorie");
			while (res.next()) {
				list.add(new CMCategorie(res.getInt("id_categorie"),res.getString("titre"),res.getString("visuel")));
				j++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		col_titre.setCellValueFactory(new  PropertyValueFactory<CMCategorie,String>("titre"));
		col_visuel.setCellValueFactory(new  PropertyValueFactory<CMCategorie,String>("visuel"));
		
		//table.getItems().clear();
		while (i < j)
		{
			table.getItems().addAll(list.get(i));
			i++;
		}
		System.out.println(list);
		
	}
	@Override
	public void changed(ObservableValue<? extends CMCategorie> arg0, CMCategorie arg1, CMCategorie arg2) {
		btn_suppr.setDisable(false);		
	}
    
    
}