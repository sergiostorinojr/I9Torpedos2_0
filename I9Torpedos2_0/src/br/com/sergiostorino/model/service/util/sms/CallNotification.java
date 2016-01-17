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
import org.smslib.ICallNotification;

/**
 * Classe Responsavel por notificar Chamadas para o SIM
 * @author Junior 
 */
public class CallNotification implements ICallNotification {

    private Properties modem1 = new Properties();
    private Properties modem2 = new Properties();
    private static final Log log = LogFactory.getLog(CallNotification.class);

    public CallNotification() {
        try {
            modem1.load(getClass().getResourceAsStream("/serialModem.properties"));
            modem2.load(getClass().getResourceAsStream("/serialModem.properties"));

        } catch (IOException ex) {
            Logger.getLogger(CallNotification.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo Responsável por receber por parametro a porta de entrada da
     * chamada, e o numero que esta realizando a chamada para o chip SIM.
     *
     * faz a verificando estando entre as condições abaixo realiza a
     * persistencia e notifica o remetente da ligação com SMS
     *
     * @param gateway
     * @param numero
     */
    @Override
    public void process(AGateway gateway, String numero) {
        if (gateway.getGatewayId().equalsIgnoreCase(modem1.getProperty("sms1.service.modem"))) {

            log.info("SMS Informativo sobre o SIM Encaminhado para o Remetente da Chamada ".concat(numero));

        } else if (gateway.getGatewayId().equalsIgnoreCase(modem2.getProperty("sms2.service.modem"))) {

            log.info("SMS Informativo sobre o SIM Encaminhado para o Remetente da Chamada ".concat(numero));

        }

    }

}
