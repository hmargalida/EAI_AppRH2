/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.enumeration;

/**
 *
 * @author Heloise
 */
public enum Equipement {
    VIDEO_PROJECTEUR("Vidéo projecteur"), ORDINATEUR("Ordinateur"), ENCEINTE("Enceinte audio"), TABLEAU("Tableau"), TABLES("Tables"), CHAISES("Chaises"); 
    
    private String libelle;
    
    Equipement() {
        
    }
    
    Equipement(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
