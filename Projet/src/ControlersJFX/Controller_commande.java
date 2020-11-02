package ControlersJFX;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import metier.CMCategorie;
import metier.CMClient;
import metier.CMCommande;
import metier.CMLignedeCommande;
import metier.CMProduit;
import sql.Connexion;

public class Controller_commande implements Initializable,ChangeListener<CMCommande>{
	int id_com = -1;
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	Connection cnx = Connexion.getInstance().getcon();
    @FXML
    private TableView<CMCommande> table;
    @FXML
    private TableView<CMLignedeCommande> tablelignecom;
    @FXML
    private TableColumn<CMCommande, Date> col_date;

    @FXML
    private TableColumn<CMCommande,String> col_id;
    @FXML
    private TableColumn<CMLignedeCommande,Integer> col_idprod;

    @FXML
    private TableColumn<CMLignedeCommande,Integer> col_qté;

    @FXML
    private TableColumn<CMLignedeCommande,Double> col_prix;

    @FXML
    private TextField Tf_id;

    @FXML
    private DatePicker datepick;

    @FXML
    private Button btn_add;
    @FXML
    private Button btn_suppr;
    @FXML
    private Button btn_suppr1;
    @FXML
    private Button btn_inf;
    @FXML
    private ChoiceBox<CMClient> ChoiceB;
    @FXML
    private ChoiceBox<CMClient> ChoiceB1;
    @FXML
    private ChoiceBox<CMProduit> ChoiceBP;
    @FXML
    private CheckBox check;
    @FXML
    private CheckBox check1;
    @FXML
    private AnchorPane comback;
    @FXML
    private Label numcom;
    @FXML
    private TextField lbl_qté;
    @FXML
    private Button btn_add1;
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
    
    
    public void tabview() {
    	ObservableList<CMCommande> list=  FXCollections.observableArrayList();
    	table.getItems().clear();
    	try {
    		ResultSet res= cnx.createStatement().executeQuery("Select * from Commande");
    		while (res.next()) {
    			list.add(new CMCommande(res.getInt("id_commande"),res.getDate("date_commande"),res.getInt("id_client")));
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	table.getItems().addAll(list);
    	
    }
    
    public void tabtri(int id_client) {
    	ObservableList<CMCommande> list=  FXCollections.observableArrayList();
    	table.getItems().clear();
    	try {
    		PreparedStatement statement =cnx.prepareStatement("Select * from Commande where id_client=?");
    		statement.setInt(1,id_client);
    		ResultSet res= statement.executeQuery();
    		while (res.next()) {
    			list.add(new CMCommande(res.getInt("id_commande"),res.getDate("date_commande"),res.getInt("id_client")));
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	table.getItems().addAll(list);

    } 
    public void tablignecom(int id_com) {
    	ObservableList<CMLignedeCommande> list2=  FXCollections.observableArrayList();
    	tablelignecom.getItems().clear();
    	try {
    		PreparedStatement statement =cnx.prepareStatement("Select * from Ligne_commande where id_commande=?");
    		statement.setInt(1,id_com);
    		ResultSet res= statement.executeQuery();
    		while (res.next()) {
    			list2.add(new CMLignedeCommande(res.getInt(1),res.getInt(2),res.getInt(3),res.getDouble(4)));
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	col_idprod.setCellValueFactory(new  PropertyValueFactory<CMLignedeCommande, Integer>("id_produit"));
    	col_qté.setCellValueFactory(new  PropertyValueFactory<CMLignedeCommande,Integer>("quantite"));
    	col_prix.setCellValueFactory(new  PropertyValueFactory<CMLignedeCommande,Double>("tarif_unitaire"));
    	tablelignecom.getItems().addAll(list2);

    }
    public void add(ActionEvent event) throws IOException {
	    LocalDate pick = datepick.getValue();
	    Date date=Date.valueOf(pick);
	    int id=ChoiceB1.getSelectionModel().getSelectedItem().getId_client();
		try {
			daos.getCommandeDAO().create(new CMCommande(date,id));
			table.getItems().addAll(new CMCommande(date,id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		datepick.setValue(null);
		btn_add.setDisable(true);
	}
    
    
    public void addlc(ActionEvent event) throws IOException{
    	int i=Integer.parseInt(lbl_qté.getText().trim());
	    int id=ChoiceBP.getSelectionModel().getSelectedItem().getId_produit();
	    float tarif=ChoiceBP.getSelectionModel().getSelectedItem().getTarif();
		try {
			daos.getLignedeCommandeDAO().create(new CMLignedeCommande(id_com,id,i,tarif));
			tablignecom(id_com);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ChoiceBP.setValue(null);
		lbl_qté.clear();
		btn_add.setDisable(true);
    	
    }
    public void tri() {
    	tabtri(ChoiceB.getSelectionModel().getSelectedItem().getId_client());
    }
	
	public void triage(ActionEvent event) throws IOException {
		if (check.isSelected()){
			ChoiceB.setDisable(false);

		}
		else {
			ChoiceB.setDisable(true);
			comback.setPrefWidth(500);
			tabview();
			}
		
	}
	
	public void test() {
	        if (datepick.getValue()!=null && ChoiceB1.getSelectionModel().getSelectedIndex()!=-1) {
	        	btn_add.setDisable(false);
	        }
	}

	public void info(ActionEvent event) {
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		if (check1.isSelected()){
			window.setHeight(1000);
			window.centerOnScreen();
		}
		else {
			btn_suppr1.setDisable(true);
			tablelignecom.getSelectionModel().clearSelection(); 
			window.setHeight(550);  
			window.centerOnScreen();
			}
	}
	
	
	public void testlc() {
		boolean qté= lbl_qté.getText().trim().matches("^\\d+$");
		if(id_com!=-1 && qté==true && ChoiceBP.getSelectionModel().getSelectedIndex()!=-1)
			btn_add1.setDisable(false);
		else btn_add1.setDisable(true);
		
		
	}
	
	public void suppr(){
		btn_suppr.setDisable(true);
		CMCommande selectedItem = table.getSelectionModel().getSelectedItem();
		try {
			daos.getCommandeDAO().delete(new CMCommande(selectedItem.getId()));
			table.getItems().remove(selectedItem);
		} catch (Exception e) {				
			e.printStackTrace();
		}
		}

	
	public void suppr1(){
		btn_suppr1.setDisable(true);
		CMLignedeCommande selectedItem = tablelignecom.getSelectionModel().getSelectedItem();
		try {
			daos.getLignedeCommandeDAO().delete(new CMLignedeCommande(selectedItem.getId_commande(),selectedItem.getId_produit()));
			tablignecom(id_com);
		} catch (Exception e) {				
			e.printStackTrace();
		}
		}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		col_date.setCellValueFactory(new  PropertyValueFactory<CMCommande, Date>("date_commande"));
    	col_id.setCellValueFactory(new  PropertyValueFactory<CMCommande,String>("id_client"));
		this.table.getSelectionModel().selectedItemProperty().addListener(this);
		comback.setPrefHeight(550);
		btn_add1.setDisable(true);
		btn_add.setDisable(true);
		check.setSelected(false);
		ChoiceB.setDisable(true);
		tabview();
		ChoiceB.setConverter(new StringConverter<CMClient>() {
	        @Override
	        public CMClient fromString(String nom) {
	            return ChoiceB.getItems().stream().filter(client -> client.getNom().equals(nom)).findFirst().orElse(null);
	        }

	        @Override
	        public String toString(CMClient objet) {
	            return objet.getNom();
	        }
	    });
		try {	
		ChoiceB.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		ChoiceB1.setConverter(new StringConverter<CMClient>() {
	        @Override
	        public CMClient fromString(String nom) {
	            return ChoiceB1.getItems().stream().filter(client -> client.getNom().equals(nom)).findFirst().orElse(null);
	        }

	        @Override
	        public String toString(CMClient objet) {
	            return objet.getNom();
	        }
	    });
		try {
			ChoiceB1.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ChoiceBP.setConverter(new StringConverter<CMProduit>() {
	        @Override
	        public CMProduit fromString(String nom) {
	            return ChoiceBP.getItems().stream().filter(prod -> prod.getNom().equals(nom)).findFirst().orElse(null);
	        }

	        @Override
	        public String toString(CMProduit objet) {
	            return objet.getNom();
	        }
	    });
		try {
			ChoiceBP.getItems().addAll(DAOFactory.getDAOFactory(Persistance.MYSQL).getProduitDAO().findAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void change(){
    	btn_suppr1.setDisable(false);
    }

	@Override
	public void changed(ObservableValue<? extends CMCommande> arg0, CMCommande arg1, CMCommande arg2) {
		
		CMCommande selectedItem = table.getSelectionModel().getSelectedItem();
		id_com = selectedItem.getId();
		testlc();
		numcom.setText("Commande n°" + id_com);
		btn_suppr.setDisable(false);
		tablignecom(id_com);
		
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
