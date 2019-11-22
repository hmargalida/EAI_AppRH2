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
    Indisponible("Indisponible"), Pressenti("Pressenti"), Affecte("Affecté"), Disponible("Disponible");
    
    private String libelle; 
    
    StatutFormateur(String libelle) {
        this.libelle = libelle;
    }
}
