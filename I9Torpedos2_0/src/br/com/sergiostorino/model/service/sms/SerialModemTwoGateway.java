/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.service.sms;

import br.com.sergiostorino.model.service.util.sms.CallNotification;
import br.com.sergiostorino.model.service.util.sms.GatewayStatusNotification;
import br.com.sergiostorino.model.service.util.sms.InboundNotification;
import br.com.sergiostorino.model.service.util.sms.OrphanedMessageNotification;
import br.com.sergiostorino.model.service.util.sms.OutboundNotification;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.AGateway;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

/**
 * Classe responsável por Comunicar com a porta de entrada
 *
 * @author Junior
 */
public class SerialModemTwoGateway implements Modem {

    private Properties props = new Properties();
    private SerialModemGateway gateway;
    private InboundNotification inboundNotification;
    private CallNotification callNotification;
    private GatewayStatusNotification statusNotification;
    private OrphanedMessageNotification orphanedMessageNotification;
    private OutboundNotification outboundNotification;

    public SerialModemTwoGateway() {
        this.inboundNotification = new InboundNotification();
        this.callNotification = new CallNotification();
        this.statusNotification = new GatewayStatusNotification();
        this.orphanedMessageNotification = new OrphanedMessageNotification();
        this.outboundNotification = new OutboundNotification();

        try {
            this.props.load(getClass().getResourceAsStream("/serialModem.properties"));

        } catch (IOException ex) {
            Logger.getLogger(SerialModemOneGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método responsável por retornar SerialModemGateway
     *
     * @return
     */
    @Override
    public SerialModemGateway getGatewayConfig() {

        if (this.gateway == null) {

            this.gateway = new SerialModemGateway(
                    this.props.getProperty("sms2.service.modem"),
                    this.props.getProperty("sms2.service.porta"),
                    Integer.parseInt(this.props.getProperty("sms2.service.taxaTransmissao")),
                    this.props.getProperty("sms2.service.fabricante"),
                    this.props.getProperty("sms2.service.modelo"));
            this.gateway.setInbound(Boolean.parseBoolean(this.props.getProperty("sms2.service.inbound")));
            this.gateway.setOutbound(Boolean.parseBoolean(this.props.getProperty("sms2.service.outbound")));
            this.gateway.setSimPin(this.props.getProperty("sms2.service.pin1"));
            this.gateway.setSimPin2(this.props.getProperty("sms2.service.pin2"));
            this.gateway.setProtocol(AGateway.Protocols.PDU);

            Service.getInstance().setOutboundMessageNotification(this.outboundNotification);
            Service.getInstance().setInboundMessageNotification(this.inboundNotification);
            Service.getInstance().setCallNotification(this.callNotification);
            Service.getInstance().setGatewayStatusNotification(this.statusNotification);
            Service.getInstance().setOrphanedMessageNotification(this.orphanedMessageNotification);

            return this.gateway;

        }

        return this.gateway;
    }

}
