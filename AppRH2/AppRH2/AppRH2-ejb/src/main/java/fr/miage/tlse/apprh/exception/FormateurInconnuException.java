/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.tlse.apprh.exception;

/**
 *
 * @author Heloise
 */
public class FormateurInconnuException extends Exception {
    
    public FormateurInconnuException() {
        
    }
    
    public FormateurInconnuException(String msg) {
        super(msg);
    }
}