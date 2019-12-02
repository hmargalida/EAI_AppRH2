/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.export;

import fr.miage.tlse.apprh.entities.DisponibiliteFormateur;
import fr.miage.tlse.apprh.enumeration.Competence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heloise
 */
public class FormateurExport implements Serializable{
    private Long idFormateur;
    
    private String nom;
    
    private String prenom;

    List<Competence> listeCompetences;
    
    List<DisponibiliteFormateurExport> disponibiliteFormateur;
    
    /**
     * Constructeur d'un objet Client
     */
    protected FormateurExport() {
    }

    /**
     * Constructeur d'un objet Formateur
     * @param prenom prĂ©nom du formateur
     * @param nom nom du formateur
     */
    public FormateurExport(String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
        this.listeCompetences = new ArrayList<>();
        this.disponibiliteFormateur = new ArrayList<>();
    }

    public Long getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(Long idFormateur) {
        this.idFormateur = idFormateur;
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

    public List<Competence> getListeCompetences() {
        return listeCompetences;
    }

    public void setListeCompetences(List<Competence> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    public List<DisponibiliteFormateurExport> getDisponibiliteFormateur() {
        return disponibiliteFormateur;
    }

    public void setDisponibiliteFormateur(List<DisponibiliteFormateurExport> disponibiliteFormateur) {
        this.disponibiliteFormateur = disponibiliteFormateur;
    }

    
    
}
