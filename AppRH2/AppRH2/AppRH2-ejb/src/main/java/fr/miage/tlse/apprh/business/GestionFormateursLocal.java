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
import java.util.Date;
import java.util.List;
import java.util.Map;
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
     */
    void ajouterCompetencesAuFormateur(long idFormateur, List<Competence> competences) throws FormateurInconnuException;
    
    /**
     * Edition de la liste des formateurs disponibles
     * A partir de la liste de formateurs compétents fourni par AppTechniCom via le JMS
     * @return Map<Integer, List
     */
    
    Map<Long, Map<Long, List<Integer>>> editerListeFormateursDispos();
    
    /**
     * Affecter un formateur à une session
     * @param idFormateur identifiant du formateur
     * @param idSession identifiant de la session
     * @param date date de la session
     */
    void affecterFormateur(long idFormateur, long idSession, Date date);
    
    /**
     * Mettre à jour le statut d'un formateur  
     * @param statut nouveau statut du formateur
     * @param date date pour laquelle le statut du formateur change
     */
    void majStatutFormateur(long idFormateur, StatutFormateur statut, int numSem) throws FormateurInconnuException;
    
    String test(String msg);
}
