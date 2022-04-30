/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

/**
 *
 * @author Inclusiv
 */
public class Etudiant implements Comparable{
    private String nom;
    private String prenom;
    private String sexe;
    private String etablissement;
    private String localisation;
    private String secteur;
    private String rentree;
    private int id=0;

    public Etudiant() {
    }

    


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setFormation(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getRentree() {
        return rentree;
    }

    public void setRentree(String rentree) {
        this.rentree = rentree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Object t) {
       
        Etudiant etudiantCompare = (Etudiant)t;
        return nom.compareTo(etudiantCompare.nom);
    
    }

}
