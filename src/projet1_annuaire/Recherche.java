/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**
 *
 * @author Inclusiv Academy 03
 */
public class Recherche{
    ObservableList<Etudiant> list = observableArrayList();
    ObservableList<Etudiant> resultat = observableArrayList();

    

    public Recherche() {
    }
    
    public void searchItem(String etudiantCherche){
            String line;
            int id = 1;

          try {
            String file = "src\\projet1_annuaire\\donnees_Projet.txt";
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            
            TableColumn rentree_Universitaire = new TableColumn("rentree");
            TableColumn nom = new TableColumn("nom");
            TableColumn prenom = new TableColumn("prenom");
            TableColumn localisation = new TableColumn("localisation");
            TableColumn rgp_formation = new TableColumn("etablissement");
            TableColumn secteur = new TableColumn("secteur");
            TableColumn sexe = new TableColumn("sexe");
            
            while ((line = br.readLine()) != null) {
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
            
            String[] arrayRecherche = etudiantCherche.toLowerCase().split(" ");
            
            for (Etudiant e : list) {
                for (String a : arrayRecherche) {
                    if (e.toString().toLowerCase().contains(a)) {
                        resultat.add(e);
                    }
                }
            }
        } catch (IOException iOException) {
              System.out.println("Erreur ::: class recherche"+ iOException);
        }
          
          
    }
     public ObservableList<Etudiant> getResultat() {
        return resultat;
    }

    public void setResultat(ObservableList<Etudiant> resultat) {
        this.resultat = resultat;
    }  
}
