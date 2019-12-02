/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.export;

import fr.miage.tlse.apprh.enumeration.StatutSession;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Heloise
 */
public class SessionExport {
    
    private long idSession;
    private long idFormateur;
    private StatutSession statut;
    private int numSemaine;
    private long idSalle;
    private long idFormation;
    private Calendar createdDate;
    private Date date;
    
    public SessionExport() {
        
    }

    public SessionExport(long idSession, long idFormateur, StatutSession statut, int numSemaine) {
        this.idSession = idSession;
        this.idFormateur = idFormateur;
        this.statut = statut;
        this.numSemaine = numSemaine;
    }

    public long getIdSession() {
        return idSession;
    }

    public void setIdSession(long idSession) {
        this.idSession = idSession;
    }

    public long getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(long idFormateur) {
        this.idFormateur = idFormateur;
    }

    public StatutSession getStatut() {
        return statut;
    }

    public void setStatut(StatutSession statut) {
        this.statut = statut;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public void setNumSemaine(int numSemaine) {
        this.numSemaine = numSemaine;
    }
}
