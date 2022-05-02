/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Inclusiv
 */
public class AjoutController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private ComboBox<String> cbSexe;
    @FXML
    private TextField tfLocalisation;
    @FXML
    private ComboBox<String> cbSecteur;
    @FXML
    private ComboBox<String> cbEtablissement;
    
    HomeController hc = new HomeController();
    ObservableList <String> listSx;
    @FXML
    private TextField fieldRecherche;
    @FXML
    private Button btnRecherche;
    @FXML
    private TableView<Etudiant> tableview;
    @FXML
    private TableColumn<Etudiant, Integer> colid;
    @FXML
    private TableColumn<Etudiant, String> colnom;
    @FXML
    private TableColumn<Etudiant, String> colprenom;
    @FXML
    private TableColumn<Etudiant, String> colsexe;
    @FXML
    private TableColumn<Etudiant, String> coletablissement;
    @FXML
    private TableColumn<Etudiant, String> colsecteur;
    @FXML
    private TableColumn<Etudiant, String> collocalisation;
    @FXML
    private TableColumn<Etudiant, String> colrentree;

    public AjoutController() throws IOException {
        this.listSx = hc.makelist();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println(this.listSx);
        cbSexe.setItems(this.listSx);
        
    }    

    @FXML
    private void ActionAjout(ActionEvent event) {
    }

    
    @FXML
    private void ActionAnnuler(ActionEvent event) {
        tfNom.setText("");
        tfPrenom.setText("");
        tfLocalisation.setText("");
        System.out.print("ioooo ");
        
     
    }

    @FXML
    private void actionRecherche(MouseEvent event) {
    }
    
}
