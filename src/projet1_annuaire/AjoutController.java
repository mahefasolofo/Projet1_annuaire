/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TextField tfLocalisation;
    @FXML
    private TextField tfSexe;
    @FXML
    private TextField tfSecteur;
    @FXML
    private TextField tfEtablissement;
    @FXML
    private TextField tfRentree;
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
    
    HomeController hc = new HomeController();
    ObservableList<Etudiant> list = observableArrayList();
    ObservableList<Etudiant> resultat = observableArrayList();
//    ObservableList <String> listSx;
    @FXML
    private TextField tfId;
    
            

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }  
    
    public ObservableList<Etudiant> getEtudiantList(){
        String line;
        int id = 1;
        try{
            
            String file = "src\\projet1_annuaire\\donnees_Projet.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8));
           
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
   
            colid.setCellValueFactory(new PropertyValueFactory<>("id"));
            colrentree.setCellValueFactory(new PropertyValueFactory<>("rentree"));
            colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colprenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            collocalisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
            coletablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
            colsecteur.setCellValueFactory(new PropertyValueFactory<>("secteur"));
            colsexe.setCellValueFactory(new PropertyValueFactory<>("sexe"));
            
            Collections.sort(list);  
//            tableview.setItems(null);
        }
        catch (FileNotFoundException ex) {
           
        } catch (IOException ex) {
           
        }
        return list;
    }
    
    public void recherche(){
        getEtudiantList();
        resultat.clear();
        String[] arrayRecherche = fieldRecherche.getText().toLowerCase().split(" ");
        list.forEach((e) -> {
            for (String a : arrayRecherche) {
                if (e.toString().toLowerCase().contains(a)) {
                    resultat.add(e);
                }
            }
        });
        tableview.setItems(resultat);
        
    }
    
    @FXML
    private void ActionAjout(ActionEvent event) {
        //récupérer les valeurs dans les textField on mettant Nom en UpperCase et Prenom première lettre majuscule reste pas
        String nomProvisoire = tfNom.getText().toUpperCase();
        String prenomProvisoire = tfPrenom.getText();
        
        String firstLtr = prenomProvisoire.substring(0, 1);
        String restLtrs = prenomProvisoire.substring(1, prenomProvisoire.length());
        firstLtr = firstLtr.toUpperCase();
        restLtrs = restLtrs.toLowerCase();
        prenomProvisoire = firstLtr + restLtrs;
        
        String localisationProvisoire = tfLocalisation.getText();
        String sexeProvisoire = tfSexe.getText();
        String secteurProvisoire = tfSecteur.getText();
        String etablissementProvisoire = tfEtablissement.getText();
        String rentreeProvisoire = tfRentree.getText();
        //les valeurs obtenues sont stockés dans un arrayInsert :
        String[] arrayInsert = {rentreeProvisoire,localisationProvisoire,etablissementProvisoire,secteurProvisoire,sexeProvisoire,nomProvisoire,prenomProvisoire};
        
        //Utilisation de BufferedWriter pour ajouter les données reçu dans notre fichier .txt /!\fichier .txt d'essai : lien à changer plus tard ! 
        try {
            String filePath = "src\\projet1_annuaire\\donnees_ajoutees.txt";
            FileWriter fw = new FileWriter(filePath, true);            
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(arrayInsert[0]+";"+arrayInsert[1]+";"+arrayInsert[2]+";"+arrayInsert[3]+";"+arrayInsert[4]+";"+arrayInsert[5]+";"+arrayInsert[6]+"\n");
            bw.close();
        } catch (IOException iOException) {
            System.out.println("Erreur ajout ::: "+iOException);
        }
        
    }

    
    @FXML
    private void ActionAnnuler(ActionEvent event) {
        tfNom.setText("");
        tfPrenom.setText("");
        tfLocalisation.setText("");
        tfSexe.setText("");
        tfSecteur.setText("");
        tfEtablissement.setText("");
        
     
    }

    @FXML
    private void actionRecherche(MouseEvent event) {
       recherche();
    }

    @FXML
    private void tvMouseAction(MouseEvent event) {
        Etudiant e=tableview.getSelectionModel().getSelectedItem();
        tfNom.setText(""+e.getNom());
        tfPrenom.setText(""+e.getPrenom());
        tfSexe.setText(""+e.getSexe());
        tfLocalisation.setText(""+e.getLocalisation());
        tfSecteur.setText(""+e.getSecteur());
        tfEtablissement.setText(""+e.getEtablissement());
        tfId.setText(""+e.getId());
    }

    @FXML
    private void ActionModifier(MouseEvent event) {
        //récupérer les données dans les TextField dans les variables provisoires
        String nomProvisoire = tfNom.getText().toUpperCase();
        String prenomProvisoire = tfPrenom.getText();
        
        String firstLtr = prenomProvisoire.substring(0, 1);
        String restLtrs = prenomProvisoire.substring(1, prenomProvisoire.length());
        firstLtr = firstLtr.toUpperCase();
        restLtrs = restLtrs.toLowerCase();
        prenomProvisoire = firstLtr + restLtrs;
        
        String localisationProvisoire = tfLocalisation.getText();
        String sexeProvisoire = tfSexe.getText();
        String secteurProvisoire = tfSecteur.getText();
        String etablissementProvisoire = tfEtablissement.getText();
        String rentreeProvisoire = tfRentree.getText();
        //les valeurs obtenues sont stockés dans un arrayInsert :
        String[] arrayInsert = {rentreeProvisoire,localisationProvisoire,etablissementProvisoire,secteurProvisoire,sexeProvisoire,nomProvisoire,prenomProvisoire};
        System.out.println(arrayInsert[0]+arrayInsert[1]+arrayInsert[2]+arrayInsert[3]+arrayInsert[4]+arrayInsert[5]+arrayInsert[6]);
    }

    @FXML
    private void ActionSupprimer(MouseEvent event) {
    }
    
}
