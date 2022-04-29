/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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
    private ComboBox<?> cbSexe;
    @FXML
    private TextField tfLocalisation;
    @FXML
    private ComboBox<?> cbSecteur;
    @FXML
    private ComboBox<?> cbEtablissement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajout(MouseEvent event) {
        
        
    }

    @FXML
    private void annuler(MouseEvent event) {
        tfNom.setText("");
        tfPrenom.setText("");
        tfLocalisation.setText("");
        HomeController hc = new HomeController ();
        System.out.println("liste : "+hc.listSexe);
        
    }
    
}
