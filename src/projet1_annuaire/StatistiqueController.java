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
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Inclusiv Academy
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<?,?> barchart;
    @FXML
    private NumberAxis genreOrdonnee;
    @FXML
    private CategoryAxis etablissementAbscisse;
    @FXML
    private ComboBox<?> cbStat;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        XYChart.Series graphe = new XYChart.Series<>();
        String line;
        Map<String,Integer> map=new HashMap<>();
        
      
        String file = "src\\projet1_annuaire\\data\\donnees_ajoutees.txt";
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList<Etudiant> list = FXCollections.observableArrayList();
        try {
            while((line = br.readLine()) != null){
                Etudiant etudiant = new Etudiant();
                String[] tempArray = line.split(";");
                
                etudiant.setRentree(tempArray[0]);
                etudiant.setLocalisation(tempArray[1]);
                etudiant.setFormation(tempArray[2]);
                etudiant.setSecteur(tempArray[3]);
                etudiant.setSexe(tempArray[4]);
                 if(map.containsKey(tempArray[2])){
                    int frequence = map.get(tempArray[2]);
                    frequence++;
                    map.put(tempArray[2], frequence);
                }else{
                    map.put(tempArray[2], 1);
                }
                
                etudiant.setNom(tempArray[5]);
                etudiant.setPrenom(tempArray[6]);
                
                
                list.add(etudiant);
            }
        } catch (IOException ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           graphe.setName(" Effectif des étudiants pour chaque Etablissements ");
           for (Map.Entry<String, Integer> entry: map.entrySet()){
           graphe.getData().add(new XYChart.Data(entry.getValue(), entry.getKey()));
           }
           barchart.getData().add(graphe);
 }
}
 
