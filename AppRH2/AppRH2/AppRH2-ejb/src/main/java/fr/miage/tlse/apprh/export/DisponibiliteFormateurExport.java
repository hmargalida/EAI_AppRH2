/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.export;

import fr.miage.tlse.apprh.enumeration.StatutFormateur;
import java.io.Serializable;

/**
 *
 * @author Heloise
 */
public class DisponibiliteFormateurExport implements Serializable{
    private int numSemaine;
    
    private StatutFormateur statutFormateur;
    
    public DisponibiliteFormateurExport() {
    }
    
    public DisponibiliteFormateurExport(int numSem, StatutFormateur statut) {
        this.numSemaine = numSem;
        this.statutFormateur = statut;
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
}
