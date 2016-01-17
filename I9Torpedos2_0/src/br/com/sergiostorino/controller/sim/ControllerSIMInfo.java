/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.controller.sim;

import br.com.sergiostorino.model.domain.SMSSendMessage;
import br.com.sergiostorino.model.domain.SendSMS;
import org.smslib.OutboundMessage;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 * @param <T>
 * @param <J>
 */
public interface ControllerSIMInfo< T extends SendSMS, J extends SMSSendMessage> {

    public T saveSendSMS(T sendSMS, J smsSendMessage, OutboundMessage msg);

    public void saveGatewayModem(Integer MODEM, SerialModemGateway gateway);

}
