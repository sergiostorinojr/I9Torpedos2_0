/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.controller.consumer;

import br.com.sergiostorino.controller.repository.ControllerReceivedSIM;
import br.com.sergiostorino.model.domain.ReceivedSIM1;
import br.com.sergiostorino.model.domain.ReceivedSIM2;
import br.com.sergiostorino.model.domain.ReceivedSIM3;
import br.com.sergiostorino.model.domain.ReceivedSIM4;
import br.com.sergiostorino.model.domain.ReceivedSIM5;
import br.com.sergiostorino.model.service.exception.PersistenceException;
import br.com.sergiostorino.model.service.sync.Bridge;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Junior
 */
public class ConsumerReceivedSIM implements Runnable {

    private final Bridge bridge;

    public ConsumerReceivedSIM(Bridge bridge) {
        this.bridge = bridge;
    }

    @Override
    public void run() {

        try {
            if (bridge instanceof ReceivedSIM1) {
                new ControllerReceivedSIM<ReceivedSIM1>(ReceivedSIM1.class).save((ReceivedSIM1) bridge.get());

            } else if (bridge instanceof ReceivedSIM2) {
                new ControllerReceivedSIM<ReceivedSIM2>(ReceivedSIM2.class).save((ReceivedSIM2) bridge.get());
            
            } else if (bridge instanceof ReceivedSIM3) {
                new ControllerReceivedSIM<ReceivedSIM3>(ReceivedSIM3.class).save((ReceivedSIM3) bridge.get());

            } else if (bridge instanceof ReceivedSIM4) {
                new ControllerReceivedSIM<ReceivedSIM4>(ReceivedSIM4.class).save((ReceivedSIM4) bridge.get());

            } else if (bridge instanceof ReceivedSIM5) {
                new ControllerReceivedSIM<ReceivedSIM5>(ReceivedSIM5.class).save((ReceivedSIM5) bridge.get());

            }
        } catch (PersistenceException | InterruptedException ex) {
            Logger.getLogger(ConsumerReceivedSIM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
