/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.producer;

import br.com.sergiostorino.controller.repository.ControllerSMSSendMessage;
import br.com.sergiostorino.controller.repository.ControllerSendSIM;
import br.com.sergiostorino.controller.sim.ControllerSIM;
import br.com.sergiostorino.controller.sim.ControllerSIMInfo;
import br.com.sergiostorino.model.domain.SMSSendMessage;
import br.com.sergiostorino.model.domain.SendSIM4;
import br.com.sergiostorino.model.domain.SendSMS;
import br.com.sergiostorino.model.service.exception.PersistenceException;
import br.com.sergiostorino.model.service.sms.SerialModemFourGateway;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.smslib.GatewayException;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 */
public class Modem4Producer implements Runnable {

    private Queue<SMSSendMessage> fila;
    private static final Log log = LogFactory.getLog(Modem4Producer.class);
    private final SerialModemGateway modem;
    private ControllerSIMInfo controllerSIMInfo;
    private Integer TIPO_MODEM = 1;
    private Integer TEMPO = 1000;
    private SendSIM4 sim;

    public Modem4Producer(Queue<SMSSendMessage> fila) {
        this.modem = new SerialModemFourGateway().getGatewayConfig();
        this.controllerSIMInfo = new ControllerSIM();
        this.fila = new LinkedList<>();
        this.fila.addAll(fila);
        this.sim = new SendSIM4();

        try {
            Service.getInstance().addGateway(modem);
            Service.getInstance().startService();

            this.controllerSIMInfo.saveGatewayModem(this.TIPO_MODEM, this.modem);

        } catch (GatewayException ex) {
            Logger.getLogger(Modem4Producer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SMSLibException ex) {
            Logger.getLogger(Modem4Producer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Modem4Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        while (!getFila().isEmpty()) {

            try {
                Thread.sleep(TEMPO);

                SMSSendMessage sMSSendMessage = getFila().peek();
                OutboundMessage msg = new OutboundMessage(sMSSendMessage.getNumero(), sMSSendMessage.getMensagem());
                msg.setEncoding(Message.MessageEncodings.ENCUCS2);

                Service.getInstance().sendMessage(msg);
                log.warn(msg);

                SendSMS saveSendSMS = new ControllerSIM().saveSendSMS(this.sim, sMSSendMessage, msg);

                new ControllerSMSSendMessage<SMSSendMessage>(SMSSendMessage.class).save(sMSSendMessage);
                new ControllerSendSIM<SendSIM4>(SendSIM4.class).save((SendSIM4) saveSendSMS);
                log.info("SMS para o numero: " + sMSSendMessage.getNumero() + " Enviado com sucesso");
                getFila().poll();

            } catch (InterruptedException | TimeoutException | GatewayException | IOException | PersistenceException ex) {
                Logger.getLogger(Modem4Producer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * @return the fila
     */
    public Queue<SMSSendMessage> getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(Queue<SMSSendMessage> fila) {
        this.fila = fila;
    }

}
