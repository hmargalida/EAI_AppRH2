/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.export;

/**
 *
 * @author Heloise
 */
public class CompetenceExport {
    
    public String libelle;
    
    public CompetenceExport() {
    }
    
    public CompetenceExport(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
