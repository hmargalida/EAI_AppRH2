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
public enum StatutSession {
    EN_PROJET("En projet"), PLANIFIEE("Planifié"), ANNULEE("Annulée"), TERMINEE("Terminée");
    
    private String libelle; 
    
    StatutSession() {
        
    }
    
    StatutSession(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
