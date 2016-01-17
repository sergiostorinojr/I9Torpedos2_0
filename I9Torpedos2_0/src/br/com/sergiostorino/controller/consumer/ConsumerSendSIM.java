/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.controller.consumer;

import br.com.sergiostorino.controller.repository.ControllerReceivedSIM;
import br.com.sergiostorino.controller.repository.ControllerSMSSendMessage;
import br.com.sergiostorino.controller.repository.ControllerSendSIM;
import br.com.sergiostorino.model.domain.ReceivedSIM1;
import br.com.sergiostorino.model.domain.ReceivedSIM2;
import br.com.sergiostorino.model.domain.ReceivedSIM3;
import br.com.sergiostorino.model.domain.ReceivedSIM4;
import br.com.sergiostorino.model.domain.ReceivedSIM5;
import br.com.sergiostorino.model.domain.SMSSendMessage;
import br.com.sergiostorino.model.domain.SendSIM1;
import br.com.sergiostorino.model.domain.SendSIM2;
import br.com.sergiostorino.model.domain.SendSIM3;
import br.com.sergiostorino.model.domain.SendSIM4;
import br.com.sergiostorino.model.domain.SendSIM5;
import br.com.sergiostorino.model.service.exception.PersistenceException;
import br.com.sergiostorino.model.service.sync.Bridge;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class ConsumerSendSIM implements Runnable {

    private final Bridge bridge;

    public ConsumerSendSIM(Bridge bridge) {
        this.bridge = bridge;
    }

    @Override
    public void run() {

        try {
           if (bridge instanceof SendSIM1) {
                new ControllerSendSIM<SendSIM1>(SendSIM1.class).save((SendSIM1) bridge.get());

            } else if (bridge instanceof SendSIM2) {
                new ControllerSendSIM<SendSIM2>(SendSIM2.class).save((SendSIM2) bridge.get());
            
            } else if (bridge instanceof SendSIM3) {
                new ControllerSendSIM<SendSIM3>(SendSIM3.class).save((SendSIM3) bridge.get());

            } else if (bridge instanceof SendSIM4) {
                new ControllerSendSIM<SendSIM4>(SendSIM4.class).save((SendSIM4) bridge.get());

            } else if (bridge instanceof SendSIM5) {
                new ControllerSendSIM<SendSIM5>(SendSIM5.class).save((SendSIM5) bridge.get());

            }

        } catch (PersistenceException | InterruptedException ex) {
            Logger.getLogger(ConsumerSendSIM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
