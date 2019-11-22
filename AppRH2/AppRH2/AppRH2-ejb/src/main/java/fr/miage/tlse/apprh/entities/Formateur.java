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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Heloise
 */
@Entity
public class Formateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFormateur;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    List<Competence> listeCompetences;
    
    Map<Integer, StatutFormateur> planningFormateur;
    
    /**
     * Constructeur d'un objet Client
     */
    protected Formateur() {
    }

    /**
     * Constructeur d'un objet Formateur
     * @param prenom prénom du formateur
     * @param nom nom du formateur
     */
    public Formateur(String prenom, String nom) {
        this.nom = nom;
        this.prenom = prenom;
        this.listeCompetences = new ArrayList<>();
        this.planningFormateur = new HashMap<>();
        int numSemaine = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int nombreSemaineTotal = Calendar.getInstance().getActualMaximum(Calendar.WEEK_OF_YEAR);
        for(int i=numSemaine; i<=nombreSemaineTotal; i++) {
            planningFormateur.put(i, StatutFormateur.Disponible);
        }
    }

    public Long getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(Long idFormateur) {
        this.idFormateur = idFormateur;
    }

    public List<Competence> getListeCompetences() {
        return listeCompetences;
    }

    public void setListeCompetences(List<Competence> listeCompetences) {
        this.listeCompetences = listeCompetences;
    }

    public Map<Integer, StatutFormateur> getPlanningFormateur() {
        return planningFormateur;
    }

    public void setPlanningFormateur(Map<Integer, StatutFormateur> planningFormateur) {
        this.planningFormateur = planningFormateur;
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
        hash = 97 * hash + Objects.hashCode(this.idFormateur);
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
        if (!Objects.equals(this.idFormateur, other.idFormateur)) {
            return false;
        }
        return true;
    }
    
    public boolean possedeCompetence(Competence c) {
        if(this.listeCompetences.contains(c)) {
            return true;
        }
        return false;
    }
    
    public void addCompetence(Competence c) {
        this.listeCompetences.add(c);
    }
    
    public List<Integer> getDisponibiliteFormateur() {
        List<Integer> listDisponibiliteFormateur = new ArrayList<>();
        for(int numSemaine : this.planningFormateur.keySet()) {
            if(this.planningFormateur.get(numSemaine).equals(StatutFormateur.Disponible)) {
                listDisponibiliteFormateur.add(numSemaine);
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
        return "Formateur{" + "idFormateur=" + idFormateur + ", nom=" + nom + ", prenom=" + prenom + ", listeCompetences=" + listeCompetences + ", planningFormateur=" + planningFormateur + '}';
    }
}
