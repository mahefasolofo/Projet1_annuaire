/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Inclusiv Academy 03
 */
public class Recherche{
    ObservableList<Etudiant> list = observableArrayList();
    ObservableList<Etudiant> resultat = observableArrayList();
    
    HomeController home = new HomeController();

    
    /*
    A FAIRE : 
        - créer une methode pour charger les donnes dans une file 
        - créer une méthode pour charger le tableau
    
    */
    

    public Recherche() {
    }
    
     public ObservableList<Etudiant> recherche(String fieldRecherche){
        home.tableset();
        resultat.clear();
        
        HashMap<Integer, Integer> resultOccurence = new HashMap<Integer, Integer>();
        String[] arrayRecherche= fieldRecherche.toLowerCase().split(" ");
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
       
        return resultat;
        
    }
    
}
