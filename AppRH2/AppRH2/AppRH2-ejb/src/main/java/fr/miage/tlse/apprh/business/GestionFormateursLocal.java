/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.business;

import fr.miage.tlse.apprh.entities.Formateur;
import fr.miage.tlse.apprh.exception.FormateurInconnuException;
import fr.miage.tlse.apprh.exception.MauvaisStatutPrecedentException;
import fr.miage.tlse.apprh.exception.NumeroSemaineIncorrect;
import fr.miage.tlse.apprh.export.DisponibilitesExport;
import fr.miage.tlse.apprh.export.FormateurExport;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Heloise
 */
@Local
public interface GestionFormateursLocal {
    
    /**
     * Création d'un formateur si il n'existe pas en base
     * @param nom nom du formateur 
     * @param prenom prénom du formateur
     * @return void 
     */
    Formateur creerFormateurSiInnexistant(String nom, String prenom);
    
    //void creerCompetenceSiInnexistante(String libelle);
    
    /**
     * Ajouter une liste de compétences à un formateur
     * @param idFormateur identifiant du formateur
     * @param competences liste de Competences
     * @return 
     * @throws fr.miage.tlse.apprh.exception.FormateurInconnuException
     */    
    Formateur ajouterCompetencesAuFormateur(long idFormateur, String competences) throws FormateurInconnuException;
    
    /**
     * Edition de la liste des formateurs disponibles
     * A partir de la liste de formateurs compétents fourni par AppTechniCom via le JMS
     * @return Map<Integer, List
     */
    List<DisponibilitesExport> editerListeFormateursDispos();
    
    /**
     * Affecter un formateur à une session
     * @return 
     * @throws fr.miage.tlse.apprh.exception.FormateurInconnuException
     * @throws fr.miage.tlse.apprh.exception.NumeroSemaineIncorrect
     * @throws fr.miage.tlse.apprh.exception.MauvaisStatutPrecedentException
     */
    FormateurExport affecterFormateur() throws FormateurInconnuException, NumeroSemaineIncorrect, MauvaisStatutPrecedentException;
    
    /**
     * Mettre à jour le statut d'un formateur
     * @param idFormateur
     * @param statut nouveau statut du formateur
     * @param numSem
     * @return 
     * @throws fr.miage.tlse.apprh.exception.FormateurInconnuException
     * @throws fr.miage.tlse.apprh.exception.NumeroSemaineIncorrect
     */
    Formateur majStatutFormateur(long idFormateur, String statut, int numSem) throws FormateurInconnuException, NumeroSemaineIncorrect, MauvaisStatutPrecedentException;
    
    String transmettreListeFormateurs();
}
