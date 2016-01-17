/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.util.sms;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message;
import org.smslib.TimeoutException;

/**
 * Classe responsável por notificar Mensagem Recebida
 *
 * @author Junior
 */
public class InboundNotification implements IInboundMessageNotification {

    private Properties modem1 = new Properties();
    private Properties modem2 = new Properties();
    private static final Log log = LogFactory.getLog(InboundNotification.class);

    public InboundNotification() {
        try {
            modem1.load(getClass().getResourceAsStream("/serialModem.properties"));
            modem2.load(getClass().getResourceAsStream("/serialModem.properties"));

        } catch (IOException ex) {
            Logger.getLogger(CallNotification.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método por processar a mensagem de entrada recebido por paramentro:
     *
     * @param gateway - Porta de entrada
     * @param msgType - Tipo da Mensagem
     * @param msg - Messagem
     *
     * Tipos de Mensagem: 
     * INBOUND - DENTRO DO LIMITE 
     * OUTBOUND - FORA DO LIMITE
     * STATUSREPORT - STATUS DO RELATÓRIO 
     * UNKNOWN - DESCONHECIDO 
     * WAPSI -
     *
     */
    @Override
    public void process(AGateway gateway, Message.MessageTypes msgType, InboundMessage msg) {
        if (msgType == Message.MessageTypes.INBOUND) {

            if (gateway.getGatewayId().equalsIgnoreCase(modem1.getProperty("sms1.service.modem"))) {

                try {
                    /**
                     * criar método de persistencia
                     * persistir mensagem
                     *
                     */
                    gateway.deleteMessage(msg);
                } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                    Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (gateway.getGatewayId().equalsIgnoreCase(modem2.getProperty("sms2.service.modem"))) {

                try {
                    /**
                     * criar método de persistencia
                     * persistir mensagem
                     *
                     */
                    gateway.deleteMessage(msg);
                } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                    Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else if (msgType == Message.MessageTypes.INBOUND) {

            if (gateway.getGatewayId().equalsIgnoreCase(modem1.getProperty("sms1.service.modem"))) {

                try {
                    /**
                     * criar método de persistencia
                     * persistir mensagem
                     *
                     */
                    gateway.deleteMessage(msg);
                } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                    Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (gateway.getGatewayId().equalsIgnoreCase(modem2.getProperty("sms2.service.modem"))) {

                try {
                    /**
                     * criar método de persistencia
                     * persistir mensagem
                     *
                     */
                    gateway.deleteMessage(msg);
                } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
                    Logger.getLogger(InboundNotification.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

}
