/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.export;

import fr.miage.tlse.apprh.enumeration.Competence;
import fr.miage.tlse.apprh.enumeration.Equipement;
import java.util.List;

/**
 *
 * @author Heloise
 */
public class SalleExport {
    
    private Long idSalle;
    
    List<Equipement> listeEquipements;
    
    public SalleExport() {
    }

    public Long getIdSalle() {
        return idSalle;
    }

    public void setIdSalle(Long idSalle) {
        this.idSalle = idSalle;
    }

    public List<Equipement> getListeEquipements() {
        return listeEquipements;
    }

    public void setListeEquipements(List<Equipement> listeEquipements) {
        this.listeEquipements = listeEquipements;
    }
    
    
}
