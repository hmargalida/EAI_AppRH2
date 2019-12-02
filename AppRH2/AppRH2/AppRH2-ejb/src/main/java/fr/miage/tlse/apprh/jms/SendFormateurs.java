/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.jms;

import fr.miage.tlse.apprh.export.FormateurExport;
import fr.miage.tlse.apprh.export.FormateursTableExport;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

/**
 *
 * @author Heloise
 */
@Stateless
public class SendFormateurs implements SendFormateursLocal {

    /**
     * Nom du Topic recherché.
     */
    @Resource(mappedName = "Formateurs")
    private Topic formateurs;
    /**
     * contexte JMS. Injection auto par serveur d'appli.
     */
    @Inject
    @JMSConnectionFactory("ConnectionFactory")
    private JMSContext context;

    @Override
    public void sendFormateurs(FormateursTableExport fe) {
        try {
            JMSProducer producer = context.createProducer();

            ObjectMessage mess = context.createObjectMessage((Serializable) fe);
            mess.setJMSType("formateurs");
            context.createProducer().send(formateurs, mess);
            System.out.println(fe + " envoyée.");

        } catch (JMSException ex) {
            Logger.getLogger(SendFormateurs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
