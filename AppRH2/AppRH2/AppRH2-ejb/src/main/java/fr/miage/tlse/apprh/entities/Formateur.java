/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.entities;

import fr.miage.tlse.apprh.enumeration.Competence;
import fr.miage.tlse.apprh.enumeration.StatutFormateur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Heloise
 */
@Entity
public class Formateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;

    @ElementCollection
    @OneToMany
    List<Competence> listeCompetences;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formateur")  
    List<DisponibiliteFormateur> disponibiliteFormateur;
    
    /**
     * Constructeur d'un objet Client
     */
    protected Formateur() {
    }

    /**
     * Constructeur d'un objet Formateur
     * @param prenom prĂ©nom du formateur
     * @param nom nom du formateur
     */
    public Formateur(String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
        this.listeCompetences = new ArrayList<>();
        this.disponibiliteFormateur = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Competence> getListeCompetences() {
        return listeCompetences;
    }

    public void setListeCompetences(List<Competence> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    public List<DisponibiliteFormateur> getDisponibiliteFormateur() {
        return disponibiliteFormateur;
    }

    public void setDisponibiliteFormateur(List<DisponibiliteFormateur> disponibiliteFormateur) {
        this.disponibiliteFormateur = disponibiliteFormateur;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Formateur other = (Formateur) obj;
        return Objects.equals(this.id, other.id);
    }
    
    public boolean possedeCompetence(Competence c) {
        return this.listeCompetences.contains(c);
    }
    
    public void addCompetence(Competence c) {
        this.listeCompetences.add(c);
    }
    
    public void addDisponibilite(DisponibiliteFormateur dispo) {
        this.disponibiliteFormateur.add(dispo);
    }
    
    public List<Integer> getDisponibilitesDuFormateur() {
        List<Integer> listDisponibiliteFormateur = new ArrayList<>();
        for(DisponibiliteFormateur dispo : this.disponibiliteFormateur) {
            if(dispo.getStatutFormateur().equals(StatutFormateur.DISPONIBLE)) {
                listDisponibiliteFormateur.add(dispo.getNumSemaine());
            }
        }
        return listDisponibiliteFormateur;
    }
    
    public boolean isAnneeBisextile() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    @Override
    public String toString() {
        return "Formateur{" + "idFormateur=" + id + ", nom=" + nom + ", prenom=" + prenom + ", listeCompetences=" + listeCompetences + ", planningFormateur=" + disponibiliteFormateur + '}';
    }
}
