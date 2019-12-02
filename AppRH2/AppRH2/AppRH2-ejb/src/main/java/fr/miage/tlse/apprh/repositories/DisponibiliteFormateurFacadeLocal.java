/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.repositories;

import fr.miage.tlse.apprh.entities.DisponibiliteFormateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Heloise
 */
@Local
public interface DisponibiliteFormateurFacadeLocal {

    void create(DisponibiliteFormateur disponibiliteFormateur);

    void edit(DisponibiliteFormateur disponibiliteFormateur);

    void remove(DisponibiliteFormateur disponibiliteFormateur);

    DisponibiliteFormateur find(Object id);

    List<DisponibiliteFormateur> findAll();

    List<DisponibiliteFormateur> findRange(int[] range);

    int count();
    
}
