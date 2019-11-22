/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.repositories;

import fr.miage.tlse.apprh.entities.Formateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Heloise
 */
@Local
public interface FormateurFacadeLocal {

    void create(Formateur formateur);

    void edit(Formateur formateur);

    void remove(Formateur formateur);

    Formateur find(Object id);

    List<Formateur> findAll();

    List<Formateur> findRange(int[] range);

    int count();
    
}
