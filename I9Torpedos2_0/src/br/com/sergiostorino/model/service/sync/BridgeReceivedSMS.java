/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.sync;

import br.com.sergiostorino.model.domain.SMSReceived;
import java.io.Serializable;

/**
 *
 * @author Junior
 * @param <T>
 */
public class BridgeReceivedSMS<T extends SMSReceived> implements Bridge<T>, Serializable {

    private Boolean disponivel = false;
    private T obj;

    public BridgeReceivedSMS() {
    }

    public BridgeReceivedSMS(T obj) {
        this.obj = obj;
    }

    @Override
    public synchronized void set(T obj) throws InterruptedException {
        while (disponivel) {

            System.err.println("Esperando set ponte BridgeReceivedSMS ");
            wait();
        }
        this.obj = obj;
        this.disponivel = true;
        notifyAll();

    }

    @Override
    public synchronized T get() throws InterruptedException {
        while (!disponivel) {
            System.err.println("Esperando get ponte BridgeReceivedSMS ");
            wait();
        }
        disponivel = false;
        notifyAll();
        return obj;
    }

}
