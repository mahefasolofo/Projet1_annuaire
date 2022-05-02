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
    
    public void recherche(String fieldRecherche){
        resultat.clear();
        String[] arrayRecherche = fieldRecherche.toLowerCase().split(" ");
            for (Etudiant e : list) {
                for (String a : arrayRecherche) {
                    if (e.toString().toLowerCase().contains(a)) {
                        resultat.add(e);
                    }
                }
            }
         
    }

    public ObservableList<Etudiant> getResultat() {
        System.out.println(resultat.toString());
        return resultat;
    }
   
}
