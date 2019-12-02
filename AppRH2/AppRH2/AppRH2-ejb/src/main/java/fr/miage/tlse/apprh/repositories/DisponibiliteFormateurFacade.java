/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.repositories;

import fr.miage.tlse.apprh.entities.DisponibiliteFormateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Heloise
 */
@Stateless
public class DisponibiliteFormateurFacade extends AbstractFacade<DisponibiliteFormateur> implements DisponibiliteFormateurFacadeLocal {

    @PersistenceContext(unitName = "RhPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DisponibiliteFormateurFacade() {
        super(DisponibiliteFormateur.class);
    }
    
}
