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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import projet1_annuaire.Etudiant;
import projet1_annuaire.HomeController;
import projet1_annuaire.Recherche;

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
    private TextField tfSecteur;
    @FXML
    private ComboBox<String> cbEtablissement;
    @FXML
    private TextField tfRentree;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfRecherche;
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
    
//Déclaration des variables
    HomeController hc = new HomeController();
    ObservableList<Etudiant> list = observableArrayList();
    ObservableList<Etudiant> resultat = observableArrayList();
    String file = "src\\projet1_annuaire\\data\\donnees_ajoutees.txt";
    Recherche rech = new Recherche();
    //Création des dictionnaires pour les comboboxes
    HashMap <String, Integer> dictSexe = new HashMap <>();
    ObservableList <String> listSexe = FXCollections.observableArrayList();
    HashMap <String, Integer> dictEtablissement = new HashMap <>();
    ObservableList <String> listEtablissement = FXCollections.observableArrayList();
    int frequence;
    
    
//Constructeur par défaut
    public AjoutController() {
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
       setCombobox(cbSexe, listSexe, dictSexe, 4);
       setCombobox(cbEtablissement, listEtablissement , dictEtablissement, 2);
       getEtudiantList();
       tableview.setItems(list);
        
    }  
    
//Actions
    @FXML
    private void ActionAjout(ActionEvent event) {
        ajouter();
        //Effacer les entrées dans les textField
        annuler();
        
    }

    @FXML
    private void ActionAnnuler(ActionEvent event) {
        annuler();
     
    }
    

    @FXML
    private void actionRecherche(MouseEvent event) {

       recherche();
    }

    @FXML
    private void tvMouseAction(MouseEvent event) {
        Etudiant e = tableview.getSelectionModel().getSelectedItem();
        
        tfNom.setText(e.getNom());
        tfPrenom.setText(e.getPrenom());
        cbSexe.setValue(e.getSexe());
        tfLocalisation.setText(e.getLocalisation());
        tfSecteur.setText(e.getSecteur());
        cbEtablissement.setValue(e.getEtablissement());
        tfId.setText(""+e.getId());
        tfRentree.setText(e.getRentree());
    }

    @FXML
    private void ActionModifier(MouseEvent event) throws IOException {
        modifier();
        annuler();
        
    }

    @FXML
    private void ActionSupprimer(MouseEvent event) {
        supprimer();
        annuler();
    }

 //Méthodes fonctionnalités
    public ObservableList<Etudiant> getEtudiantList(){
        String line;
        int id = 1;
        list.clear();
        try{
            
            
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

        }
        catch (FileNotFoundException ex) {
           
        } catch (IOException ex) {
           
        }
        return list;
    }
    
    public void recherche(){
        getEtudiantList();
        resultat.clear();
        tableview.setItems(null);
        HashMap<Integer, Integer> resultOccurence = new HashMap<Integer, Integer>();
        String[] arrayRecherche= tfRecherche.getText().toLowerCase().split(" ");
        List<Integer> listId = new ArrayList<Integer>();
        for(Etudiant e:list){
            for (String a : arrayRecherche) {
                if(e.toString().toLowerCase().contains(a)) {
                    if(resultOccurence.containsKey(e.getId()) != true){
                        resultOccurence.put(e.getId(), 1);
                    }else{
                        resultOccurence.merge(e.getId(), 1, Integer::sum);
                    }
                 }
            }
        }
        for ( int key : resultOccurence.keySet() ) {
            if(resultOccurence.get(key) == arrayRecherche.length){
                listId.add(key);
            }
        }
        
        for(Etudiant e:list){
            for(int id : listId){
                if(id == e.getId())
                    resultat.add(e);
            }
        }
        listId.clear();
        
        tableview.setItems(resultat);
        
    }
    
    private void modifier() throws IOException{
        FileWriter w= new FileWriter(file,false);
        BufferedWriter bw = new BufferedWriter(w);
        //Ecrire sur textField
         Etudiant e = tableview.getSelectionModel().getSelectedItem();
         
     
    //Récupérer les valeurs de l'élément sélectionner dans le textFIeld et les mettre dans des variables temporaires
        String nomProvisoire = tfNom.getText().toUpperCase();
        String prenomProvisoire = tfPrenom.getText();
        String firstLtr = prenomProvisoire.substring(0, 1);
        String restLtrs = prenomProvisoire.substring(1, prenomProvisoire.length());
        firstLtr = firstLtr.toUpperCase();
        restLtrs = restLtrs.toLowerCase();
        prenomProvisoire = firstLtr + restLtrs;
        String localisationProvisoire = tfLocalisation.getText();
        String sexeProvisoire = cbSexe.getValue();
        String secteurProvisoire = tfSecteur.getText();
        String etablissementProvisoire = cbEtablissement.getValue();
        String rentreeProvisoire = tfRentree.getText();
        int idProvisoire = Integer.parseInt(tfId.getText());
         
        //supprimer l'Etudiant dans la liste
        for(Etudiant a:list){
            if(e.getId() == a.getId()){
                list.remove(a);
                break;
            }
        } 
        
        //Créer un nouveau Etudiant et  l'insérer dans la liste
        Etudiant newEtudiant = new Etudiant(idProvisoire, nomProvisoire, prenomProvisoire, sexeProvisoire, etablissementProvisoire, localisationProvisoire, secteurProvisoire, rentreeProvisoire);
        list.add(newEtudiant);
        
        //pour chaque Etudiant dans la nouvelle liste ajouter dans bw 
        for(Etudiant i:list){
            bw.write(i.toString2());
        }
         bw.close();
        
        tableview.setItems(list);
        hc.setList(list);
        
    }
    
    private void supprimer(){
         try{
               FileWriter w= new FileWriter(file,false);

             for(Etudiant e:list){
                 if(e.getId()== tableview.getSelectionModel().getSelectedItem().getId()){
                   list.remove(e);
                   
                   break;
                 }
             }
             for(Etudiant e:resultat){
                 if(e.getId()== tableview.getSelectionModel().getSelectedItem().getId()){
                  resultat.remove(e);
                   break;
                 }
             }
             for(Etudiant e:list){
                w.write(e.toString2());
                
             }
             w.close();
       }catch(IOException e){
       }
          tableview.setItems(list);
    }
    
    public void ajouter(){
        //récupérer les valeurs dans les textField on mettant Nom en UpperCase et Prenom première lettre majuscule reste pas
        String nomProvisoire = tfNom.getText().toUpperCase();
        String prenomProvisoire = tfPrenom.getText();
        
        String firstLtr = prenomProvisoire.substring(0, 1);
        String restLtrs = prenomProvisoire.substring(1, prenomProvisoire.length());
        firstLtr = firstLtr.toUpperCase();
        restLtrs = restLtrs.toLowerCase();
        prenomProvisoire = firstLtr + restLtrs;
        
        String localisationProvisoire = tfLocalisation.getText();
        String sexeProvisoire = cbSexe.getValue();
        String secteurProvisoire = tfSecteur.getText();
        String etablissementProvisoire = cbEtablissement.getValue();
        String rentreeProvisoire = tfRentree.getText();
        //les valeurs obtenues sont stockés dans un arrayInsert :
        //String[] arrayInsert = {rentreeProvisoire,localisationProvisoire,etablissementProvisoire,secteurProvisoire,sexeProvisoire,nomProvisoire,prenomProvisoire};
        Etudiant newEtudiant = new Etudiant(0, nomProvisoire, prenomProvisoire, sexeProvisoire, etablissementProvisoire, localisationProvisoire, secteurProvisoire, rentreeProvisoire);
        //Utilisation de BufferedWriter pour ajouter les données reçu dans notre fichier .txt /!\fichier .txt d'essai : lien à changer plus tard ! 
        try {
           
            FileWriter fw = new FileWriter(file, true);            
            BufferedWriter bw = new BufferedWriter(fw);
            list.add(newEtudiant);
        
            bw.write(newEtudiant.toString2());
            bw.close();
        } catch (IOException iOException) {
            System.out.println("Erreur ajout ::: "+iOException);
        }
        tableview.setItems(list);
        
    }
    
    public void annuler(){
       tfNom.setText("");
        tfPrenom.setText("");
        tfLocalisation.setText("");
        cbSexe.setValue("");
        tfSecteur.setText("");
        cbEtablissement.setValue("");
        tfRentree.setText("");
        tfId.setText("");
         
    }
    
//Constitution Combobox 
    
    public ObservableList<String> makelist (HashMap <String, Integer> dictionnaire, ObservableList <String> liste, int rang) throws FileNotFoundException, IOException{
    String line;

    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),StandardCharsets.UTF_8));

    while((line = br.readLine()) != null){
    String [] provisoireArray = line.split(";");
    if (dictionnaire.containsKey(provisoireArray [rang])){
        frequence = dictionnaire.get(provisoireArray[rang]);
        frequence++;
        dictionnaire.put(provisoireArray[rang], frequence);
        } else {
        dictionnaire.put(provisoireArray[rang], frequence);

        }
        }
        dictionnaire.entrySet().forEach((entry) -> {
        //System.out.println(entry.getKey());

        liste.add(entry.getKey());

        });
    return liste;
    }
    
    public void setCombobox (ComboBox<String> combobox, ObservableList <String> liste, HashMap <String, Integer> dictionnaire, int rang){
        try {
            makelist (dictionnaire, liste, rang);
            combobox.setItems(liste);
        } catch (IOException ex) {
            Logger.getLogger(AjoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
}

