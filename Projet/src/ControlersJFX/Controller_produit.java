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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import metier.CMCategorie;
import metier.CMProduit;
import sql.Connexion;

public class Controller_produit implements Initializable,ChangeListener<CMProduit>{
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	Connection cnx = Connexion.getInstance().getcon();
	@FXML
	public TableView<CMProduit> table;
	
	@FXML
	private TableColumn<CMProduit,Integer> col_idproduit;
	@FXML
	private TableColumn<CMProduit,String> col_nom;
	@FXML
	private TableColumn<CMProduit,String> col_desc;
	@FXML
	private TableColumn<CMProduit,Float> col_tarif;
	@FXML
	private TableColumn<CMProduit,String> col_visuel;
	@FXML
	private TableColumn<CMProduit,Integer> col_idcateg;
	@FXML
	public Button btn_supr;
	@FXML
	public Button btn_add;
	@FXML
	public ChoiceBox<CMCategorie> ChoiceB;
    @FXML
    private TextArea Tfdesc;

    @FXML
    private TextField Tfnom;

    @FXML
    private TextField Tftarif;

    @FXML
    private TextField Tfvisuel;
	

	
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	btn_add.setDisable(true);
	table.setEditable(true);
	col_idproduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
	col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
	col_desc.setCellFactory(TextFieldTableCell.forTableColumn());
	col_tarif.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
	this.table.getSelectionModel().selectedItemProperty().addListener(this);
	btn_supr.setDisable(true);
	tabview();
	ChoiceB.setConverter(new StringConverter<CMCategorie>() {
        @Override
        public CMCategorie fromString(String nom) {
            return ChoiceB.getItems().stream().filter(categ -> categ.getTitre().equals(nom)).findFirst().orElse(null);
        }

        @Override
        public String toString(CMCategorie objet) {
            return objet.getTitre();
        }
    });
	try {
		ChoiceB.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getCategorieDAO().findAll());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

}
public void swapaccueil(ActionEvent event){
	System.out.println("yo");
	Parent tablViewParent;
	tablViewParent=null;
	try {
		tablViewParent = FXMLLoader.load(getClass().getResource("/FXML/accueil.fxml"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Scene tablViewsScene = new Scene(tablViewParent);
	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	window.setScene(tablViewsScene);
	window.show();
}



public void modifid(CellEditEvent<?,?> edittedCell)
{
    CMProduit Prod =  table.getSelectionModel().getSelectedItem();
    String edit =edittedCell.getNewValue().toString();
    int i = Integer.parseInt(edit.trim());
    Prod.setId_produit(i);
    try{
		daos.getProduitDAO().update(new CMProduit(Prod.getId_produit(),Prod.getNom(),Prod.getDescription(),Prod.getTarif(),Prod.getVisuel(),Prod.getId_categorie()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public void modifnom(CellEditEvent<?,?> edittedCell)
{
    CMProduit Prod =  table.getSelectionModel().getSelectedItem();
    Prod.setNom(edittedCell.getNewValue().toString());
    try {
		daos.getProduitDAO().update(new CMProduit(Prod.getId_produit(),Prod.getNom(),Prod.getDescription(),Prod.getTarif(),Prod.getVisuel(),Prod.getId_categorie()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void modifdes(CellEditEvent<?,?> edittedCell)
{
    CMProduit Prod =  table.getSelectionModel().getSelectedItem();
    Prod.setDescription(edittedCell.getNewValue().toString());
    try {
		daos.getProduitDAO().update(new CMProduit(Prod.getId_produit(),Prod.getNom(),Prod.getDescription(),Prod.getTarif(),Prod.getVisuel(),Prod.getId_categorie()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public void modiftarif(CellEditEvent<?,?> edittedCell)
{
    CMProduit Prod =  table.getSelectionModel().getSelectedItem();
    String edit =edittedCell.getNewValue().toString();
    Float i=Float.parseFloat(edit.trim());
    Prod.setTarif(i);
    try{
		daos.getProduitDAO().update(new CMProduit(Prod.getId_produit(),Prod.getNom(),Prod.getDescription(),Prod.getTarif(),Prod.getVisuel(),Prod.getId_categorie()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void modifvisuel(CellEditEvent<?,?> edittedCell)
{
    CMProduit Prod =  table.getSelectionModel().getSelectedItem();
    Prod.setVisuel(edittedCell.getNewValue().toString());
    try {
		daos.getProduitDAO().update(new CMProduit(Prod.getId_produit(),Prod.getNom(),Prod.getDescription(),Prod.getTarif(),Prod.getVisuel(),Prod.getId_categorie()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public void add(ActionEvent event) throws IOException {
	
	String LblDesc = Tfdesc.getText().trim();
    String Lblnom = Tfnom.getText().trim();
    String LblTarif = Tftarif.getText().trim();
    String Lblvisuel = Tfvisuel.getText().trim();
	int value = ChoiceB.getSelectionModel().getSelectedItem().getId();  
	float f=Float.parseFloat(LblTarif);  
	System.out.println(value);
	try {
		CMProduit Prod = new CMProduit(Lblnom,LblDesc,f,Lblvisuel,value);
		daos.getProduitDAO().create(Prod);
		table.getItems().add(Prod);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Tfdesc.clear();
	Tfnom.clear();
	Tftarif.clear();
	Tfvisuel.clear();
	ChoiceB.getSelectionModel().clearSelection();
	ChoiceB.setValue(null);
	btn_add.setDisable(true);
}
public void suppr(){
	btn_supr.setDisable(true);
	CMProduit selectedItem = table.getSelectionModel().getSelectedItem();
	System.out.println(selectedItem.getNom());
	try {
		daos.getProduitDAO().delete(new CMProduit(selectedItem.getId_produit()));
		table.getItems().remove(selectedItem);
	} catch (Exception e) {				
		e.printStackTrace();
	}
	}

public void test() {
	String LblDesc = Tfdesc.getText().trim();
    String Lblnom = Tfnom.getText().trim();
    String LblTarif = Tftarif.getText().trim();
    String Lblvisuel = Tfvisuel.getText().trim();
    boolean isfloat = LblTarif.matches("[0-9]*\\.?[0-9]+");
    String value=null;
    if(ChoiceB.getSelectionModel().getSelectedIndex()!=-1)
        value = ChoiceB.getSelectionModel().getSelectedItem().getTitre();
        if (LblDesc.isEmpty()|| Lblnom.isEmpty() || LblTarif.isEmpty()||Lblvisuel.isEmpty()|| value == null|| isfloat==false) {
        	btn_add.setDisable(true);
        }
        else {btn_add.setDisable(false);}
}
public void tabview() {
	int i =0,j=0;
	ObservableList<CMProduit> list=  FXCollections.observableArrayList();
	table.getItems().clear();
	try {
		ResultSet res= cnx.createStatement().executeQuery("Select * from Produit");
		while (res.next()) {
			list.add(new CMProduit(res.getInt("id_produit"),res.getString("nom"),res.getString("description"),res.getFloat("tarif"),res.getString("visuel"),res.getInt("id_categorie")));
			j++;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	col_idproduit.setCellValueFactory(new  PropertyValueFactory<CMProduit,Integer>("id_produit"));
	col_nom.setCellValueFactory(new  PropertyValueFactory<CMProduit,String>("nom"));
	col_desc.setCellValueFactory(new  PropertyValueFactory<CMProduit,String>("description"));
	col_tarif.setCellValueFactory(new  PropertyValueFactory<CMProduit,Float>("tarif"));
	col_visuel.setCellValueFactory(new  PropertyValueFactory<CMProduit,String>("visuel"));
	col_idcateg.setCellValueFactory(new  PropertyValueFactory<CMProduit,Integer>("id_categorie"));
	//table.getItems().clear();
	while (i < j)
	{
		table.getItems().addAll(list.get(i));
		i++;
	}
	System.out.println(list);
	
}

@Override
public void changed(ObservableValue<? extends CMProduit> arg0, CMProduit arg1, CMProduit arg2) {
	btn_supr.setDisable(false);
	
}
}