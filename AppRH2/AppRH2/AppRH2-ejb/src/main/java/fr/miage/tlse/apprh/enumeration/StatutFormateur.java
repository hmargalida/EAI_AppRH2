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
public enum StatutFormateur {
    INDISPONIBLE("Indisponible"), PRESSENTI("Pressenti"), AFFECTE("Affecté"), DISPONIBLE("Disponible");
    
    private String libelle; 
    
    StatutFormateur() {
        
    }
    
    StatutFormateur(String libelle) {
        this.libelle = libelle;
    }

    public String getId() {
        return name();
    }
    
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
