/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.export;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heloise
 */
public class FormateursTableExport implements Serializable {
    
    List<FormateurExport> formateurs;

    public FormateursTableExport() {
        this.formateurs = new ArrayList<>();
    }

    public List<FormateurExport> getFormateurs() {
        return formateurs;
    }

    public void setFormateurs(List<FormateurExport> formateurs) {
        this.formateurs = formateurs;
    }
    
    public void addFormateur(FormateurExport form) {
        this.formateurs.add(form);
    }
}
