package ControlersJFX;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Client;

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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import metier.CMClient;
import metier.CMProduit;
import sql.Connexion;

public class Controller_client implements Initializable,ChangeListener<CMClient> {
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	Connection cnx = Connexion.getInstance().getcon();
    @FXML
    private AnchorPane clientback;

    @FXML
    private AnchorPane clientform;

    @FXML
    private TextField Tf_nom;

    @FXML
    private TextField Tf_prenom;

    @FXML
    private TextField Tf_id;

    @FXML
    private TextField Tf_rue;

    @FXML
    private TextField Tf_num;

    @FXML
    private TextField Tf_cp;

    @FXML
    private TextField Tf_ville;

    @FXML
    private TextField Tf_pays;

    @FXML
    private PasswordField Pf_mdp;

    @FXML
    private TableView<CMClient> table;

    @FXML
    private TableColumn<CMClient,String> col_nom;

    @FXML
    private TableColumn<CMClient,String> col_prenom;

    @FXML
    private TableColumn<CMClient,String> col_id;

    @FXML
    private TableColumn<CMClient,String> col_mdp;

    @FXML
    private TableColumn<CMClient,String> col_num;

    @FXML
    private TableColumn<CMClient,String> col_rue;

    @FXML
    private TableColumn<CMClient,String>col_cp;

    @FXML
    private TableColumn<CMClient,String> col_ville;

    @FXML
    private TableColumn<CMClient,String> col_pays;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_suppr;
    @FXML
    private Menu Macceuil;

    @FXML
    private Menu Mclient;

    @FXML
    private Menu MCommande;

    @FXML
    private Menu Mproduit;

    @FXML
    private Menu Mcatégorie;
    @FXML
    private CheckBox check1;

    @FXML
    private TextField lbl_tri;
    
    
   
    public void tri() {
    	if(!lbl_tri.getText().trim().isEmpty())
    	tabtri(lbl_tri.getText().trim());
    }
	
	public void triage(ActionEvent event) throws IOException {
		if (check1.isSelected()){
			lbl_tri.setDisable(false);

		}
		else {
			lbl_tri.setDisable(true);
			tabview();
			}
		
	}
	
	public void tabtri(String nom) {
		ObservableList<CMClient> list2=  FXCollections.observableArrayList();
		table.getItems().clear();
		try {
			PreparedStatement statement =cnx.prepareStatement("Select * from Client where nom=?");
			statement.setString(1,nom);
			ResultSet res= statement.executeQuery();
			while (res.next()) {
				list2.add(new CMClient(res.getInt("id_client"),res.getString("nom"),res.getString("prenom"),res.getString("identifiant"),res.getString("mot_de_passe"),res.getString("adr_numero"),res.getString("adr_voie"),res.getString("adr_code_postal"),res.getString("adr_ville"),res.getString("adr_pays")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.getItems().addAll(list2);

		
	}
	public void tabview() {
		int i =0,j=0;
		ObservableList<CMClient> list=  FXCollections.observableArrayList();
		table.getItems().clear();
		try {
			ResultSet res= cnx.createStatement().executeQuery("Select * from Client");
			while (res.next()) {
				list.add(new CMClient(res.getInt("id_client"),res.getString("nom"),res.getString("prenom"),res.getString("identifiant"),res.getString("mot_de_passe"),res.getString("adr_numero"),res.getString("adr_voie"),res.getString("adr_code_postal"),res.getString("adr_ville"),res.getString("adr_pays")));
				j++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		col_nom.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("nom"));
		col_prenom.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("prenom"));
		col_id.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("identifiant"));
		col_mdp.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("mot_de_passe"));
		col_num.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("adr_numero"));
		col_rue.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("adr_voie"));
		col_cp.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("adr_code_postal"));
		col_ville.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("adr_ville"));
		col_pays.setCellValueFactory(new  PropertyValueFactory<CMClient,String>("adr_pays"));


		while (i < j)
		{
			table.getItems().addAll(list.get(i));
			i++;
		}
		
	}
	public void add(ActionEvent event) throws IOException {
		
		String nom = Tf_nom.getText().trim();
	    String prenom = Tf_prenom.getText().trim();
	    String identifiant = Tf_id.getText().trim();
	    String rue = Tf_rue.getText().trim();
	    String numero = Tf_num.getText().trim();
	    String ville = Tf_ville.getText().trim();
	    String pays = Tf_pays.getText().trim();
	    String codepost = Tf_cp.getText().trim();
	    String mdp = Pf_mdp.getText().trim();
		try {
			CMClient client = new CMClient(nom,prenom,identifiant,mdp,numero,rue,codepost,ville,pays);
			daos.getClientDAO().create(client);
			table.getItems().add(client);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tf_nom.clear();
		Tf_prenom.clear();
		Tf_id.clear();
		Tf_rue.clear();
		Tf_num.clear();
		Tf_ville.clear();
		Tf_pays.clear();
		Tf_cp.clear();
		Pf_mdp.clear();
		btn_add.setDisable(true);
	}
	
	public void suppr(){
		CMClient selectedItem = table.getSelectionModel().getSelectedItem();
		try {
			int id = selectedItem.getId_client();
			daos.getClientDAO().delete(new CMClient(id));
			table.getItems().remove(selectedItem);
		} catch (Exception e) {				
			e.printStackTrace();
		}
		btn_suppr.setDisable(true);
		}

	
	public void test() {
		String nom = Tf_nom.getText().trim();
	    String prenom = Tf_prenom.getText().trim();
	    String identifiant = Tf_id.getText().trim();
	    String rue = Tf_rue.getText().trim();
	    String numero = Tf_num.getText().trim();
	    String ville = Tf_ville.getText().trim();
	    String pays = Tf_pays.getText().trim();
	    String codepost = Tf_cp.getText().trim();
	    String mdp = Pf_mdp.getText().trim();
	    if (nom.isEmpty()|| prenom.isEmpty() || identifiant.isEmpty()||rue.isEmpty()||numero.isEmpty()|| ville.isEmpty() || pays.isEmpty()||codepost.isEmpty()||mdp.isEmpty()) {
	        btn_add.setDisable(true);
	        }
	    else {btn_add.setDisable(false);}
	}
	
	
	public void modifnom(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setNom(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modifprenom(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setPrenom(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifident(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setIdentifiant(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modifmdp(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setMot_de_passe(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifnum(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setAdr_numero(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modifrue(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setAdr_voie(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifcp(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setAdr_code_postal(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void modifville(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setAdr_ville(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modifpays(CellEditEvent<?,?> edittedCell)
	{
	    CMClient client =  table.getSelectionModel().getSelectedItem();
	    client.setAdr_pays(edittedCell.getNewValue().toString());
	    try {
			daos.getClientDAO().update(new CMClient(client.getId_client(),client.getNom(),client.getPrenom(),client.getIdentifiant(),client.getMot_de_passe(),client.getAdr_numero(),client.getAdr_voie(),client.getAdr_code_postal(),client.getAdr_ville(),client.getAdr_pays()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		table.setEditable(true);
		col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
		col_prenom.setCellFactory(TextFieldTableCell.forTableColumn());
		col_id.setCellFactory(TextFieldTableCell.forTableColumn());
		col_mdp.setCellFactory(TextFieldTableCell.forTableColumn());
		col_cp.setCellFactory(TextFieldTableCell.forTableColumn());
		col_num.setCellFactory(TextFieldTableCell.forTableColumn());
		col_ville.setCellFactory(TextFieldTableCell.forTableColumn());
		col_pays.setCellFactory(TextFieldTableCell.forTableColumn());
		col_rue.setCellFactory(TextFieldTableCell.forTableColumn());
		this.table.getSelectionModel().selectedItemProperty().addListener(this);
		btn_add.setDisable(true);
		btn_suppr.setDisable(true);
		tabview();
		
	}
	@Override
	public void changed(ObservableValue<? extends CMClient> arg0, CMClient arg1, CMClient arg2) {
		btn_suppr.setDisable(false);
		
	}
	public void swapaccueil(ActionEvent event){
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
		window.setHeight(500);
		window.centerOnScreen();
		window.show();
	}


	

}
