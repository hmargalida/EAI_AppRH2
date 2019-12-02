/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.entities;

import fr.miage.tlse.apprh.enumeration.StatutFormateur;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Heloise
 */
@Entity
public class DisponibiliteFormateur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Formateur formateur;
    
    private int numSemaine;
    
    private StatutFormateur statutFormateur;
    
    public DisponibiliteFormateur() {
    }
    
    public DisponibiliteFormateur(int numSem, StatutFormateur statut) {
        this.numSemaine = numSem;
        this.statutFormateur = statut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(int numSemaine) {
        this.numSemaine = numSemaine;
    }

    public StatutFormateur getStatutFormateur() {
        return statutFormateur;
    }

    public void setStatutFormateur(StatutFormateur statutFormateur) {
        this.statutFormateur = statutFormateur;
    }

    public Formateur getFormateur() {
        return formateur;
    }

    public void setFormateur(Formateur formateur) {
        this.formateur = formateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DisponibiliteFormateur)) {
            return false;
        }
        DisponibiliteFormateur other = (DisponibiliteFormateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DisponibiliteFormateur{" + "id=" + id + ", numSemaine=" + numSemaine + ", statutFormateur=" + statutFormateur + '}';
    }    
    
}
