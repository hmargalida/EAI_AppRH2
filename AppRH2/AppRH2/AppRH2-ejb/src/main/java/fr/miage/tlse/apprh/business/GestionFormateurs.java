/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.business;

import fr.miage.tlse.apprh.enumeration.StatutFormateur;
import fr.miage.tlse.apprh.enumeration.Competence;
import fr.miage.tlse.apprh.entities.Formateur;
import fr.miage.tlse.apprh.exception.FormateurInconnuException;
import fr.miage.tlse.apprh.repositories.FormateurFacadeLocal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Heloise
 */
@Stateless
public class GestionFormateurs implements GestionFormateursLocal {
    
    @EJB
    private FormateurFacadeLocal formateurFacade;

    /**
     * Création d'un formateur si il n'existe pas en base
     * @param nom nom du formateur 
     * @param prenom prénom du formateur
     * @return void 
     */
    @Override
    public Formateur creerFormateurSiInnexistant(String nom, String prenom) {
        List<Formateur> listFormateur = formateurFacade.findAll();
        int i = 0;
        while(i<listFormateur.size()) {
            // si il existe déjà, on retourne le client via ClientExport
            if(listFormateur.get(i).getPrenom().equals(prenom) && listFormateur.get(i).getNom().equals(nom)) {
                return listFormateur.get(i);
            }
            i++;
        }
        Formateur nouveauFormateur = new Formateur(prenom, nom);
        this.formateurFacade.create(nouveauFormateur);
        return nouveauFormateur;
    }

    /**
     * Ajouter une liste de compétences à un formateur
     * @param idFormateur identifiant du formateur
     * @param competences liste de Competences
     */
    @Override
    public void ajouterCompetencesAuFormateur(long idFormateur, List<Competence> competences) throws FormateurInconnuException {
        //recherche du formateur passé en paramètre
        final Formateur f = this.formateurFacade.find(idFormateur);
        if (f == null) {
            throw new FormateurInconnuException("Formateur inconnu");
        }
        // pour chaque compétence
        for(Competence comp : competences) {
            if(!f.possedeCompetence(comp)) {
                f.addCompetence(comp);
                this.formateurFacade.edit(f);
            }
        }
    }

    /**
     * Edition de la liste des formateurs disponibles
     * A partir de la liste de formateurs compétents fourni par AppTechniCom via le JMS
     */
    @Override
    public Map<Long, Map<Long, List<Integer>>> editerListeFormateursDispos() {
        // récupération de la Map des formateurs compétents depuis le JMSProvider (de TechniCom)
        // key : idFormation, value : idFormateur
        Map<Long, List<Formateur>> listeFormateursCompetents = new HashMap<>();
        // key : idFormation, value : Map<idFormateur, List<numSemaine>>
        Map<Long, Map<Long, List<Integer>>> listDisponibilitesPourFormation = new HashMap<>();
        for(long idFormation : listeFormateursCompetents.keySet()) {
            Map<Long, List<Integer>> innerMap = new HashMap<>();
            for(Formateur f : listeFormateursCompetents.get(idFormation)) {
                List<Integer> disponibilitesFormateur = f.getDisponibiliteFormateur();
                innerMap.put(f.getIdFormateur(), disponibilitesFormateur);
            }
            listDisponibilitesPourFormation.put(idFormation, innerMap);
        }
        return listDisponibilitesPourFormation;
    }

    @Override
    public void affecterFormateur(long idFormateur, long idSession, Date date) {
        // récupération du JMSProvider de la date de la session et du premier formateur disponible 
    }

    @Override
    public void majStatutFormateur(long idFormateur, StatutFormateur statut, int numSem) throws FormateurInconnuException {
        final Formateur f = this.formateurFacade.find(idFormateur);
        if(f == null) {
            throw new FormateurInconnuException("Formateur inconnu");
        }
        Map<Integer, StatutFormateur> planning = f.getPlanningFormateur();
        if(planning.containsKey(numSem)) {
            planning.remove(numSem);
        }
        planning.put(numSem, statut);
        this.formateurFacade.edit(f);
    }

    @Override
    public String test(String msg) {
        return "toto "+msg;
    }

}
