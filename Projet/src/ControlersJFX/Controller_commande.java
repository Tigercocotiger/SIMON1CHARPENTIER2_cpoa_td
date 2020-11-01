package ControlersJFX;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import metier.CMCategorie;
import metier.CMCommande;
import sql.Connexion;

public class Controller_commande implements Initializable{
	DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
	Connection cnx = Connexion.getInstance().getcon();
    @FXML
    private TableView<CMCommande> table;

    @FXML
    private TableColumn<CMCommande, Date> col_date;

    @FXML
    private TableColumn<CMCommande,String> col_id;
    
    @FXML
    private TextField Tf_id;

    @FXML
    private DatePicker datepick;

    @FXML
    private Button btn_add;

    
    
    public void tabview() {
    	int i =0,j=0;
    	ObservableList<CMCommande> list=  FXCollections.observableArrayList();
    	table.getItems().clear();
    	try {
    		ResultSet res= cnx.createStatement().executeQuery("Select * from Commande");
    		while (res.next()) {
    			list.add(new CMCommande(res.getInt("id_commande"),res.getDate("date_commande"),res.getInt("id_client")));
    			j++;
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	col_date.setCellValueFactory(new  PropertyValueFactory<CMCommande, Date>("date_commande"));
    	col_id.setCellValueFactory(new  PropertyValueFactory<CMCommande,String>("id_client"));
    	//table.getItems().clear();
    	while (i < j)
    	{
    		table.getItems().addAll(list.get(i));
    		i++;
    	}
    	System.out.println(list);
    	
    }
    public void add(ActionEvent event) throws IOException {
		String id_client = Tf_id.getText().trim();
	    LocalDate pick = datepick.getValue();
	    Date date=Date.valueOf(pick);
	    int id=Integer.parseInt(id_client);
		try {
			daos.getCommandeDAO().create(new CMCommande(date,id));
			table.getItems().addAll(new CMCommande(date,id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tf_id.clear();
		datepick.setValue(null);
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tabview();
		
	}

}
