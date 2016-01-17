/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.util.sms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;

/**
 * Classe responsável por notificar status da porta de entrada
 *
 * @author Junior
 */
public class GatewayStatusNotification implements IGatewayStatusNotification {
    
    private static final Log log = LogFactory.getLog(GatewayStatusNotification.class);

    /**
     * Método responsável por processar o estado do gateway
     *
     * @param gateway   - Porta de Entrada
     * @param oldStatus - estado anterior
     * @param newStatus - novo estado
     */
    @Override
    public void process(AGateway gateway, AGateway.GatewayStatuses oldStatus, AGateway.GatewayStatuses newStatus) {
        
        System.out.println(">>> Mudança de status na porta de entrada para " + gateway.getGatewayId() + ", Antes: " + oldStatus + " -> Novo: " + newStatus);
    }

}
