/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.util.sms;

import org.smslib.AGateway;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.InboundMessage;

/**
 * Classe responsável por notificar as mensagem Orfão de entrada
 * 
 * @author Junior
 */
public class OrphanedMessageNotification implements IOrphanedMessageNotification {

    /**
     * Método responsavel por processar mensagem de entrada Orfão
     * recebendo por parametro
     * 
     * @param gateway
     * @param msg
     * @return
     */
    @Override
    public boolean process(AGateway gateway, InboundMessage msg) {
        
        return false;
    }

}
