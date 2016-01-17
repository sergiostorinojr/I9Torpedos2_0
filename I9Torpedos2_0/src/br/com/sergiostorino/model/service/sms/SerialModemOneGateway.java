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
public class SerialModemOneGateway implements Modem {

    private Properties props = new Properties();
    private SerialModemGateway gateway;
    private final InboundNotification inboundNotification;
    private final CallNotification callNotification;
    private final GatewayStatusNotification statusNotification;
    private final OrphanedMessageNotification orphanedMessageNotification;
    private final OutboundNotification outboundNotification;

    public SerialModemOneGateway() {

        try {
            this.props.load(getClass().getResourceAsStream("/serialModem.properties"));

        } catch (IOException ex) {
            Logger.getLogger(SerialModemOneGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.inboundNotification = new InboundNotification();
        this.callNotification = new CallNotification();
        this.statusNotification = new GatewayStatusNotification();
        this.orphanedMessageNotification = new OrphanedMessageNotification();
        this.outboundNotification = new OutboundNotification();
    }

    /**
     *Método responsável por retornar SerialModemGateway
     * @return
     */
    @Override
    public SerialModemGateway getGatewayConfig() {

        if (this.gateway == null) {

            this.gateway = new SerialModemGateway(
                    this.props.getProperty("sms1.service.modem"),
                    this.props.getProperty("sms1.service.porta"),
                    Integer.parseInt(this.props.getProperty("sms1.service.taxaTransmissao")),
                    this.props.getProperty("sms1.service.fabricante"),
                    this.props.getProperty("sms1.service.modelo"));
            this.gateway.setInbound(Boolean.parseBoolean(this.props.getProperty("sms1.service.inbound")));
            this.gateway.setOutbound(Boolean.parseBoolean(this.props.getProperty("sms1.service.outbound")));
            this.gateway.setSimPin(this.props.getProperty("sms1.service.pin1"));
            this.gateway.setSimPin2(this.props.getProperty("sms1.service.pin2"));
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
