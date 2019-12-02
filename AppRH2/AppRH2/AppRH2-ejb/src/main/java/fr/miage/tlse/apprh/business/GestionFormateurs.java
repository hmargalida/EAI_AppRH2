/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.business;

import fr.miage.tlse.apprh.entities.DisponibiliteFormateur;
import fr.miage.tlse.apprh.enumeration.StatutFormateur;
import fr.miage.tlse.apprh.enumeration.Competence;
import fr.miage.tlse.apprh.entities.Formateur;
import fr.miage.tlse.apprh.enumeration.StatutSession;
import fr.miage.tlse.apprh.exception.FormateurInconnuException;
import fr.miage.tlse.apprh.exception.MauvaisStatutPrecedentException;
import fr.miage.tlse.apprh.exception.NumeroSemaineIncorrect;
import fr.miage.tlse.apprh.export.DisponibiliteFormateurExport;
import fr.miage.tlse.apprh.export.DisponibilitesExport;
import fr.miage.tlse.apprh.export.FormateurExport;
import fr.miage.tlse.apprh.export.FormateursTableExport;
import fr.miage.tlse.apprh.export.FormationExport;
import fr.miage.tlse.apprh.export.SessionExport;
import fr.miage.tlse.apprh.jms.SendFormateurs;
import fr.miage.tlse.apprh.jms.SendFormateursLocal;
import fr.miage.tlse.apprh.repositories.DisponibiliteFormateurFacadeLocal;
import fr.miage.tlse.apprh.repositories.FormateurFacadeLocal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

    @EJB
    private DisponibiliteFormateurFacadeLocal disponibilitefacade;
    
    @EJB 
    private SendFormateursLocal sendFormateurs;

    /**
     * Création d'un formateur si il n'existe pas en base
     *
     * @param nom nom du formateur
     * @param prenom prénom du formateur
     * @return void
     */
    @Override
    public Formateur creerFormateurSiInnexistant(String nom, String prenom) {
        List<Formateur> listFormateur = formateurFacade.findAll();
        int i = 0;
        while (i < listFormateur.size()) {
            // si il existe déjà, on retourne le client via FormateurExport
            if (listFormateur.get(i).getPrenom().equals(prenom) && listFormateur.get(i).getNom().equals(nom)) {
                return listFormateur.get(i);
            }
            i++;
        }
        Formateur nouveauFormateur = new Formateur(prenom, nom);
        this.formateurFacade.create(nouveauFormateur);

        int numSemaine = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        int nombreSemaineTotal = Calendar.getInstance().getActualMaximum(Calendar.WEEK_OF_YEAR);
        for (int j = numSemaine; j <= nombreSemaineTotal; j++) {
            DisponibiliteFormateur dispo = new DisponibiliteFormateur(j, StatutFormateur.DISPONIBLE);
            dispo.setFormateur(nouveauFormateur);
            this.disponibilitefacade.create(dispo);
            nouveauFormateur.addDisponibilite(dispo);
        }
        this.formateurFacade.edit(nouveauFormateur);
        return nouveauFormateur;
    }

    /**
     * Ajouter une liste de compétences à un formateur
     *
     * @param idFormateur identifiant du formateur
     * @param comp liste de Competences
     * @return Formateur
     * @throws FormateurInconnuException
     */
    @Override
    public Formateur ajouterCompetencesAuFormateur(long idFormateur, String comp) throws FormateurInconnuException {
        //recherche du formateur passé en paramètre
        final Formateur f = this.formateurFacade.find(idFormateur);
        if (f == null) {
            throw new FormateurInconnuException("Formateur inconnu");
        }

        // pour chaque compétence
        Competence newComp = Competence.valueOf(comp);
        if (!f.possedeCompetence(newComp)) {
            f.addCompetence(newComp);
            this.formateurFacade.edit(f);
        }
        return f;
    }

    /**
     * Edition de la liste des formateurs disponibles A partir de la liste de
     * formateurs compétents fourni par AppTechniCom via le JMS
     */
    @Override
    public List<DisponibilitesExport> editerListeFormateursDispos() {
        // JMS : Réception de la liste de tous les formateurs et de la formation en question
        List<Formateur> tousLesFormateurs = formateurFacade.findAll();
        FormationExport formation = new FormationExport(0);

        DisponibilitesExport disponibilites;
        List<DisponibilitesExport> listeDesDisponibilites = new ArrayList<>();
        for (Formateur formateur : tousLesFormateurs) {
            List<Integer> numsSemaine = new ArrayList<>();
            for (DisponibiliteFormateur disponibiliteDuFormateur : formateur.getDisponibiliteFormateur()) {
                if (disponibiliteDuFormateur.getStatutFormateur().equals(StatutFormateur.DISPONIBLE)) {
                    numsSemaine.add(disponibiliteDuFormateur.getNumSemaine());
                }
            }
            disponibilites = new DisponibilitesExport(formation.getIdFormation(), formateur.getId());
            disponibilites.setListeSemainesDispo(numsSemaine);
            listeDesDisponibilites.add(disponibilites);
        }
        return listeDesDisponibilites;
    }

    @Override
    public FormateurExport affecterFormateur() throws FormateurInconnuException, NumeroSemaineIncorrect, MauvaisStatutPrecedentException {
        // récupération du JMSProvider de la Session Plannifiée/Annulée
        SessionExport sessionAnnulee = new SessionExport(1, 151, StatutSession.ANNULEE, 50);
        StatutSession statutSession = sessionAnnulee.getStatut();
        /*SessionExport sessionPlanifiee = new SessionExport(1, 151, StatutSession.PLANIFIEE, 50);
        StatutSession statutSessionPlanifiee = sessionPlanifiee.getStatut();
        SessionExport sessionEnProjet = new SessionExport(1, 151, StatutSession.EN_PROJET, 50);
        StatutSession statutSession = sessionEnProjet.getStatut();*/
        Formateur f = formateurFacade.find(sessionAnnulee.getIdFormateur());
        if(f == null) {
            throw new FormateurInconnuException("Formateur inconnu");
        }
        
        switch(statutSession) {
            case EN_PROJET:
                // on passe le formateur à pressenti
                if(!f.getDisponibilitesDuFormateur().contains(sessionAnnulee.getNumSemaine())) {
                    throw new MauvaisStatutPrecedentException("Le formateur n'est pas disponible à cette période");
                }
                this.majStatutFormateur(sessionAnnulee.getIdFormateur(), StatutFormateur.PRESSENTI.getId(), sessionAnnulee.getNumSemaine());
                break;
            case PLANIFIEE:
                // on passe le formateur à affecté 
                // on peut le passer à affecté si il est disponible avant 
                this.majStatutFormateur(sessionAnnulee.getIdFormateur(), StatutFormateur.AFFECTE.getId(), sessionAnnulee.getNumSemaine());
                break;
            case ANNULEE:
                // on passe le formateur à dispo
                // on peut le passer à dispo si il était affecté ou pressenti
                this.majStatutFormateur(sessionAnnulee.getIdFormateur(), StatutFormateur.DISPONIBLE.getId(), sessionAnnulee.getNumSemaine());
                break;
            default:
                break;
                
        }
        // RETOUR
        FormateurExport retour = new FormateurExport(f.getPrenom(), f.getNom());
        List<DisponibiliteFormateur> planning = f.getDisponibiliteFormateur();
        List<DisponibiliteFormateurExport> exportPlanning = new ArrayList<>();
        for(DisponibiliteFormateur disp : planning) {
            DisponibiliteFormateurExport export = new DisponibiliteFormateurExport(disp.getNumSemaine(), disp.getStatutFormateur());
            exportPlanning.add(export);
        }
        retour.setDisponibiliteFormateur(exportPlanning);
        return retour;
    }

    /**
     * Mise à jour du statut d'un formateur à une date donnée
     *
     * @param idFormateur
     * @param statut
     * @param numSem
     * @return
     * @throws FormateurInconnuException
     * @throws fr.miage.tlse.apprh.exception.NumeroSemaineIncorrect
     */
    @Override
    public Formateur majStatutFormateur(long idFormateur, String statut, int numSem) throws FormateurInconnuException, NumeroSemaineIncorrect {
        final Formateur f = this.formateurFacade.find(idFormateur);
        if (f == null) {
            throw new FormateurInconnuException("Formateur inconnu");
        }
        int nombreSemaineTotal = Calendar.getInstance().getActualMaximum(Calendar.WEEK_OF_YEAR);
        int numSemaineActuelle = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
        if(numSem > nombreSemaineTotal || numSem <= numSemaineActuelle) {
            throw new NumeroSemaineIncorrect("Le numéro de la semaine renseigné est incorrect");
        }
        List<DisponibiliteFormateur> planning = f.getDisponibiliteFormateur();
        StatutFormateur newStatut = StatutFormateur.valueOf(statut);
        for (DisponibiliteFormateur dispo : planning) {
            if (dispo.getNumSemaine() == numSem) {
                dispo.setStatutFormateur(newStatut);
            }
        }
        this.formateurFacade.edit(f);
        return f;
    }

    @Override
    public String transmettreListeFormateurs() {
        FormateursTableExport export = new FormateursTableExport();
        List<Formateur> formateurs = this.formateurFacade.findAll();
        for(Formateur f : formateurs) {
            FormateurExport fe = new FormateurExport(f.getPrenom(), f.getNom());
            fe.setListeCompetences(f.getListeCompetences());
            List<DisponibiliteFormateur> planning = f.getDisponibiliteFormateur();
            List<DisponibiliteFormateurExport> exportPlanning = new ArrayList<>();
            for (DisponibiliteFormateur disp : planning) {
                DisponibiliteFormateurExport exportDispo = new DisponibiliteFormateurExport(disp.getNumSemaine(), disp.getStatutFormateur());
                exportPlanning.add(exportDispo);
            }
            fe.setDisponibiliteFormateur(exportPlanning);
            export.addFormateur(fe);
        }
        this.sendFormateurs.sendFormateurs(export);
        return "La liste a été envoyée";
    }
    
    
}
