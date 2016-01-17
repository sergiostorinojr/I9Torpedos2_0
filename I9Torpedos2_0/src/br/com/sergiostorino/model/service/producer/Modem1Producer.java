/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.producer;

import br.com.sergiostorino.controller.repository.ControllerRepository;
import br.com.sergiostorino.controller.repository.ControllerSMSSendMessage;
import br.com.sergiostorino.controller.repository.ControllerSendSIM;
import br.com.sergiostorino.controller.sim.ControllerSIM;
import br.com.sergiostorino.controller.sim.ControllerSIMInfo;
import br.com.sergiostorino.model.domain.SMSSendMessage;
import br.com.sergiostorino.model.domain.SendSIM1;
import br.com.sergiostorino.model.domain.SendSMS;
import br.com.sergiostorino.model.service.exception.PersistenceException;
import br.com.sergiostorino.model.service.sms.SerialModemTwoGateway;
import java.io.IOException;
import java.util.Iterator;
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
public class Modem1Producer implements Runnable {

    private Queue<SMSSendMessage> fila;
    private static final Log log = LogFactory.getLog(Modem1Producer.class);
    private final SerialModemGateway modem;
    private ControllerSIMInfo controllerSIMInfo;
    private Integer TIPO_MODEM = 1;
    private Integer TEMPO = 1000;
    private SendSIM1 sim;
    private ControllerRepository<SMSSendMessage> repoSMSSendMessage;
    private ControllerRepository<SendSIM1> repoSendSIM1;

    public Modem1Producer(Queue<SMSSendMessage> fila) {
        this.modem = new SerialModemTwoGateway().getGatewayConfig();
        this.controllerSIMInfo = new ControllerSIM();
        this.fila = new LinkedList<>();
        this.fila.addAll(fila);
        this.sim = new SendSIM1();
        this.repoSMSSendMessage = new ControllerSMSSendMessage<>(SMSSendMessage.class);
        this.repoSendSIM1 = new ControllerSendSIM<>(SendSIM1.class);

        try {
            Service.getInstance().addGateway(modem);
            Service.getInstance().startService();

            this.controllerSIMInfo.saveGatewayModem(this.TIPO_MODEM, this.modem);

        } catch (GatewayException ex) {
            Logger.getLogger(Modem1Producer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SMSLibException ex) {
            Logger.getLogger(Modem1Producer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Modem1Producer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        while (!getFila().isEmpty()) {

            try {
                Thread.sleep(TEMPO);

                Iterator<SMSSendMessage> iterator = getFila().iterator();

                while (iterator.hasNext()) {
                    SMSSendMessage sMSSendMessage = iterator.next();
                    OutboundMessage msg = new OutboundMessage(sMSSendMessage.getNumero(), sMSSendMessage.getMensagem());
                    msg.setEncoding(Message.MessageEncodings.ENCUCS2);

                    Service.getInstance().sendMessage(msg);
                    log.warn(msg);

                    SendSMS saveSendSMS = new ControllerSIM().saveSendSMS(this.sim, sMSSendMessage, msg);

                    this.repoSMSSendMessage.save(sMSSendMessage);
                    this.repoSendSIM1.save((SendSIM1) saveSendSMS);
                    log.info("SMS para o numero: " + sMSSendMessage.getNumero() + " Enviado com sucesso");
                }
                getFila().clear();

            } catch (InterruptedException | TimeoutException | GatewayException | IOException | PersistenceException ex) {
                Logger.getLogger(Modem1Producer.class.getName()).log(Level.SEVERE, null, ex);
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
