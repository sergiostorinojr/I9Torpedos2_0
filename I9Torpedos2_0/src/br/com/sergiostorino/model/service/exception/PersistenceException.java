/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.exception;

/**
 *
 * @author Junior
 */
public class PersistenceException extends Exception {

    public PersistenceException(String msg, Exception e) {
        super(msg, e);
    }

    public PersistenceException(String msg) {
        super(msg);
    }

}
