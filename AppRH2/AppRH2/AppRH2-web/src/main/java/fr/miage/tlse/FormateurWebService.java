/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse;

import com.google.gson.Gson;
import fr.miage.tlse.apprh.business.GestionFormateursLocal;
import fr.miage.tlse.apprh.entities.Formateur;
import fr.miage.tlse.apprh.exception.FormateurInconnuException;
import fr.miage.tlse.apprh.exception.MauvaisStatutPrecedentException;
import fr.miage.tlse.apprh.exception.NumeroSemaineIncorrect;
import fr.miage.tlse.apprh.export.DisponibilitesExport;
import fr.miage.tlse.apprh.export.FormateurExport;
import fr.miage.tlse.apprh.repositories.FormateurFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Heloise
 */
@Path("formateur")
@RequestScoped
public class FormateurWebService {

    GestionFormateursLocal gestionFormateurs = lookupGestionFormateursLocal();

    @Context
    private UriInfo context;

    private Gson gson = new Gson();

    /**
     * Creates a new instance of FormateurWebService
     */
    public FormateurWebService() {
    }

    /**
     * Retrieves representation of an instance of
     * fr.miage.tlse.FormateurWebService
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of FormateurWebService
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    public FormateurFacadeLocal lookupFormateurFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (fr.miage.tlse.apprh.repositories.FormateurFacadeLocal) c.lookup("java:global/AppRH2-ear/AppRH2-ejb-1.0-SNAPSHOT/FormateurFacade!fr.miage.tlse.apprh.repositories.FormateurFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private GestionFormateursLocal lookupGestionFormateursLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionFormateursLocal) c.lookup("java:global/AppRH2-ear/AppRH2-ejb-1.0-SNAPSHOT/GestionFormateurs!fr.miage.tlse.apprh.business.GestionFormateursLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    @Path("test")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public String testMsg() {
        FormateurFacadeLocal formateurFacade = lookupFormateurFacadeLocal();
        return formateurFacade.findAll().get(0).getNom();
    }

    @Path("ajoutFormateur")
    @POST()
    public String ajoutFormateur(@QueryParam("nom") String nom, @QueryParam("prenom") String prenom) {
        gestionFormateurs.creerFormateurSiInnexistant(nom, prenom);
        return "Formateur ajouté";
    }

    @Path("{idFormateur}/ajoutCompetences")
    @Produces(MediaType.APPLICATION_JSON)
    @POST()
    public String ajouterCompetencesAuFormateur(@PathParam("idFormateur") long idFormateur, @QueryParam("newCompetence") String comp) throws FormateurInconnuException {
        try {
            Formateur f = gestionFormateurs.ajouterCompetencesAuFormateur(idFormateur, comp);
            //return Response.created(context.getAbsolutePath()).entity(this.gson.toJson(f)).build();
            return "compétences ajoutée";
        } catch (FormateurInconnuException e) {
            return "Erreur lors de l'insertion de la compétence : " + e.getMessage();
        }
    }

    @Path("{idFormateur}/majStatutFormateur")
    @Produces(MediaType.APPLICATION_JSON)
    @POST()
    public String majStatutFormateur(@PathParam("idFormateur") long idFormateur, @QueryParam("numSem") int numSem, @QueryParam("statut") String statut) {
        try {
            Formateur f = gestionFormateurs.majStatutFormateur(idFormateur, statut, numSem);
            return "Planning du formateur mis à jour ! ";
        } catch (FormateurInconnuException | NumeroSemaineIncorrect | MauvaisStatutPrecedentException ex) {
            return "Erreur lors de la mise à jour : " + ex.getMessage();
        }
    }

    @Path("editerListeFormateursDispo")
    @Produces(MediaType.APPLICATION_JSON)
    @GET()
    public String editerListeFormateurDispo() {
        List<DisponibilitesExport> disponibilites = gestionFormateurs.editerListeFormateursDispos();
        String jsonString = gson.toJson(disponibilites);
        return jsonString;
    }

    @Path("affecterFormateur")
    @Produces(MediaType.APPLICATION_JSON)
    @POST()
    public String affecterFormateur() {
        try {
            FormateurExport formateur = gestionFormateurs.affecterFormateur();
            String jsonString = gson.toJson(formateur);
            return jsonString;
        } catch (FormateurInconnuException | NumeroSemaineIncorrect | MauvaisStatutPrecedentException ex) {
            return "Erreur lors de l'affectation : " + ex.getMessage();
        }
    }
    
    @Path("transmettreListeFormateurs")
    @POST()
    public String transmettreListeFormateurs() {
        return this.gestionFormateurs.transmettreListeFormateurs();
    }

}
