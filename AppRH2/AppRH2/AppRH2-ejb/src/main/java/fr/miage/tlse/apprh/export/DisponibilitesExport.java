/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.export;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heloise
 */
public class DisponibilitesExport {
    
    private long idFormation;
    
    private long idFormateur;
    
    private List<Integer> listeSemainesDispo;
    
    public DisponibilitesExport(long idFormation, long idFormateur) {
        this.idFormateur = idFormateur;
        this.idFormation = idFormation;
        this.listeSemainesDispo = new ArrayList<>();
    }

    public long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(long idFormation) {
        this.idFormation = idFormation;
    }

    public long getIdFormateur() {
        return idFormateur;
    }

    public void setIdFormateur(long idFormateur) {
        this.idFormateur = idFormateur;
    }

    public List<Integer> getListeSemainesDispo() {
        return listeSemainesDispo;
    }

    public void setListeSemainesDispo(List<Integer> listeSemainesDispo) {
        this.listeSemainesDispo = listeSemainesDispo;
    }
}
