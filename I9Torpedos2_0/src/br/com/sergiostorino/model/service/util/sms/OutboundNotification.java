/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.util.sms;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

/**
 * Classe responsável por notificar menssagem de saida
 * 
 * @author Junior
 */
public class OutboundNotification implements IOutboundMessageNotification {

    /**
     * Método responsável por notificar as mensagens de saida recebendo por parametro
     * 
     * @param gateway
     * @param msg
     */
    @Override
    public void process(AGateway gateway, OutboundMessage msg) {
        
        //Persistir Saida ao Banco de dados
    }

}
