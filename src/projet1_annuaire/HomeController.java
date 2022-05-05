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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;


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
    private TextField tfRecherche;
    @FXML
    private Button btnRecherche;
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
    @FXML
    private Label labelUser;

//Déclaration des variables    
    ObservableList<Etudiant> list = observableArrayList();
    ObservableList<Etudiant> resultat = observableArrayList();
    String file = "src\\projet1_annuaire\\data\\donnees_ajoutees.txt";
    LoginController loginC = new LoginController();
    
    //CREATION DES DICTIONNAIRES ET LISTES /!\
    TreeMap <String, Integer> dictSexe = new TreeMap <>();
    ObservableList <String> listSexe = FXCollections.observableArrayList();
    int frequence;
    private int id = 1;
    
        
//Constructeur
    public HomeController() {
    }
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        labelUser.setText("Admin"); // à changer par la valeur dans LoginController /!\
        tableset();
        tableview.setItems(list);
    }

//Actions
    @FXML
    private void actionRecherche(MouseEvent event) {
        recherche();
        
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
    
    @FXML
    private void Deconnexion(MouseEvent event) throws IOException {
        URL url = Paths.get("src\\projet1_annuaire\\login.fxml").toUri().toURL();
        Parent source = FXMLLoader.load(url);
        Scene scene = new Scene(source);
        Stage stage = new Stage();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setX(600);
        stage.setY(200);
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    @FXML
    private void PrintTable(MouseEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
        double scaleX = pageLayout.getPrintableWidth() / tableview.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / tableview.getBoundsInParent().getHeight();

        Scale scale = new Scale(scaleX, scaleY);
        tableview.getTransforms().add(scale);


        if(job != null){
            job.printPage(pageLayout,tableview);
            job.endJob();
            tableview.getTransforms().remove(scale);
        }

    }
    
//Méthodes fonctionnalités
    public void tableset(){

        String line;
        
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
                if(provisoireArray.length==7){
                etudiant.setRentree(provisoireArray[0]);
                etudiant.setLocalisation(provisoireArray[1]);
                etudiant.setFormation(provisoireArray[2]);
                etudiant.setSecteur(provisoireArray[3]);
                etudiant.setSexe(provisoireArray[4]);
                etudiant.setNom(provisoireArray[5]);
                etudiant.setPrenom(provisoireArray[6]);
                }
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
            
            Collections.sort(list);
  
        }
        catch (FileNotFoundException ex) {
           
        } catch (IOException ex) {
           
        }
    
    }
    
    public void recherche(){
        //pour rafraichier le tableau;
        resultat.clear();
        tableview.setItems(null);
        
        TreeMap<Integer, Integer> resultOccurence = new TreeMap<Integer, Integer>();
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
    
    private void loadpage (String page){
        Parent source = null; 
        try {
            source  = FXMLLoader.load(getClass().getResource(page+".fxml"));

        } catch (IOException ex) {
            System.out.println(":::: Erreur chargement page source :::"+ex.toString());
        }
        borderpane.setCenter(source);
    }
    
//Getters & Setters    
    public TableView<Etudiant> getTableview() {
        return tableview;
    }

    public void setTableview(TableView<Etudiant> tableview) {
        this.tableview = tableview;
    }
    
    //A VERIFIER SI UTILE
    public TreeMap<String, Integer> getDictSexe() {
        return dictSexe;
    }
    
     public ObservableList<Etudiant> getList() {
        return list;
    }

    public void setList(ObservableList<Etudiant> list) {
        this.list = list;
    }

    
}
