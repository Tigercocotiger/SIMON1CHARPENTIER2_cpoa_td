package ControlersJFX;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class Controller_client {

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
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> col_nom;

    @FXML
    private TableColumn<?, ?> col_prenom;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_mdp;

    @FXML
    private TableColumn<?, ?> col_num;

    @FXML
    private TableColumn<?, ?> col_rue;

    @FXML
    private TableColumn<?, ?> col_cp;

    @FXML
    private TableColumn<?, ?> col_ville;

    @FXML
    private TableColumn<?, ?> col_pays;

}
