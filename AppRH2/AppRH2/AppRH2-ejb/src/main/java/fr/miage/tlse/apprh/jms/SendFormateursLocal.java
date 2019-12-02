/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.jms;

import fr.miage.tlse.apprh.export.FormateurExport;
import fr.miage.tlse.apprh.export.FormateursTableExport;
import javax.ejb.Local;

/**
 *
 * @author Heloise
 */
@Local
public interface SendFormateursLocal {
    
    public void sendFormateurs(FormateursTableExport fe);
}
