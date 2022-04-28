/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    ObservableList<Etudiant> list = observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableset();
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
    
    private void tableset (){
         String line;
        int id = 1;
        try{
            // InputStream :  pour avoir le fichier .txt toujours accessible en chemin relatif
            String file = "C:\\Users\\Inclusiv\\Documents\\PROJET 1\\ProjectGIT\\Projet1_annuaire\\src\\projet1_annuaire\\donnees_Projet.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8));
            System.out.println("File found");
        
            TableColumn rentree_Universitaire = new TableColumn("rentree");
            TableColumn nom = new TableColumn("nom");
            TableColumn prenom = new TableColumn("prenom");
            TableColumn localisation = new TableColumn("localisation");
            TableColumn rgp_formation = new TableColumn("etablissement");
            TableColumn secteur = new TableColumn("secteur");
            TableColumn sexe = new TableColumn("sexe");
            
           while((line = br.readLine()) != null)
           
           {
                Etudiant etudiant = new Etudiant();
                String[] provisoireArray = line.split(";");
                
                etudiant.setRentree(provisoireArray[0]);
                etudiant.setLocalisation(provisoireArray[1]);
                etudiant.setFormation(provisoireArray[2]);
                etudiant.setSecteur(provisoireArray[3]);
                etudiant.setSexe(provisoireArray[4]);
                etudiant.setNom(provisoireArray[5]);
                etudiant.setPrenom(provisoireArray[6]);
                etudiant.setId(id);
                id++;
                list.add(etudiant);
            }
           colid.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, Integer>("id")
            );
            
            colrentree.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, String>("rentree")
            );
            colnom.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, String>("nom")
            );
            colprenom.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, String>("prenom")
            );
            
            collocalisation.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, String>("localisation")
            );
            coletablissement.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, String>("etablissement")
            );
            colsecteur.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, String>("secteur")
            );
            colsexe.setCellValueFactory(
                    new PropertyValueFactory<Etudiant, String>("sexe")
            );
            tableview.setItems(list);
        }
        catch (FileNotFoundException ex) {
           
        } catch (IOException ex) {
           
        }
    
    
    
    }
    
}