/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Inclusiv
 */
public class HomeController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane anchorpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void loadpage (String page){
        Parent source = null; 
        try {
            source  = FXMLLoader.load(getClass().getResource(page+".fxml"));

        } catch (IOException ex) {
            System.out.println(":::: Erreur chargement page source :::"+ex.toString());
        }
        borderpane.setCenter(source);
    }

    @FXML
    private void home(MouseEvent event) {
        borderpane.setCenter(anchorpane);
    }

    @FXML
    private void showAjout(MouseEvent event) {
        loadpage("ajout");
    }

    @FXML
    private void showStatistique(MouseEvent event) {
        loadpage("statistique");
    }
}
