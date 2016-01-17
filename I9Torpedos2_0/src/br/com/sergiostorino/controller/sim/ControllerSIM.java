/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.controller.sim;

import br.com.sergiostorino.model.domain.GatewayModem;
import br.com.sergiostorino.model.domain.SMSSendMessage;
import br.com.sergiostorino.model.domain.SendSMS;
import br.com.sergiostorino.model.service.exception.PersistenceException;
import br.com.sergiostorino.model.service.repository.BaseRepository;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.smslib.GatewayException;
import org.smslib.OutboundMessage;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

/**
 *
 * @author Junior
 */
public class ControllerSIM implements ControllerSIMInfo<SendSMS, SMSSendMessage> {

    private Properties props;

    public ControllerSIM() {

        this.props = new Properties();
        try {
            this.props.load(getClass().getResourceAsStream("/serialModem.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ControllerSIM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param sendSMS
     * @param smsSendMessage
     * @param msg
     * @return
     */
    @Override
    public SendSMS saveSendSMS( SendSMS sendSMS, SMSSendMessage smsSendMessage, OutboundMessage msg) {

        sendSMS.setGatewayID(msg.getGatewayId());

        sendSMS.setMessageId(String.valueOf(msg.getMessageId()));
        sendSMS.setMessageUUID(msg.getUuid());
        sendSMS.setEncoding(msg.getEncoding().toString());
        sendSMS.setDataGerada(msg.getDate().toString());
        sendSMS.setSmscRefNum(msg.getRefNo());
        sendSMS.setNumDestinatario(smsSendMessage.getNumero());
        if (msg.getDispatchDate() == null) {

            sendSMS.setDataExapedicao("Não há data prevista para expedição");

        } else {
            sendSMS.setDataExapedicao(msg.getDispatchDate().toString());

        }
        sendSMS.setMsgStatus(msg.getMessageStatus().toString());
        sendSMS.setCausaFalha(String.valueOf(msg.getFailureCause()));
        sendSMS.setPeriodoValido(String.valueOf(msg.getValidityPeriod()));
        sendSMS.setRelatorioStatus(msg.getStatusReport());
        sendSMS.setPortaDestino(String.valueOf(msg.getSrcPort()));
        sendSMS.setFlashSMS(msg.getFlashSms());
        sendSMS.setMensagem(smsSendMessage.getMensagem());
        sendSMS.setPduData(msg.getPduUserData());

        if (msg.getScheduledDeliveryDate() == null) {

            sendSMS.setEntregaPrevista("SEM DATA PREVISTA");
        } else {
            sendSMS.setEntregaPrevista(msg.getScheduledDeliveryDate().toString());

        }
        return sendSMS;

    }

    /**
     *
     * @param MODEM
     * @param gateway
     */
    @Override
    public void saveGatewayModem( Integer MODEM, SerialModemGateway gateway) {
        GatewayModem gwd = new GatewayModem();
        try {
            gwd.setFabricante(gateway.getManufacturer());
            gwd.setModelo(gateway.getModel());
            gwd.setSerialNumero(gateway.getSerialNo());
            gwd.setSwVersion(gateway.getSwVersion());
            gwd.setNivelBateria(String.valueOf(gateway.getBatteryLevel()) + " %");
            gwd.setNivelSinal(String.valueOf(gateway.getSignalLevel()) + " dBm");
            gwd.setPin1(gateway.getSimPin());
            gwd.setPin2(gateway.getSimPin2());
            gwd.setImsi(gateway.getImsi());
            gwd.setIdConfigMoldem(gateway.getGatewayId());

            switch (MODEM) {

                case 1:
                    gwd.setPortaMoldem(props.getProperty("sms1.service.porta"));
                    break;

                case 2:
                    gwd.setPortaMoldem(props.getProperty("sms2.service.porta"));
                    break;
                case 3:
                    gwd.setPortaMoldem(props.getProperty("sms3.service.porta"));
                    break;
                case 4:
                    gwd.setPortaMoldem(props.getProperty("sms4.service.porta"));
                    break;
                case 5:
                    gwd.setPortaMoldem(props.getProperty("sms5.service.porta"));
                    break;

                default:
                    //nenhum modem encontrado
                    break;

            }

            String status = String.valueOf(gateway.getStatus());

            if (status.equalsIgnoreCase("true")) {
                gwd.setStatus("ONLINE");

            } else if (status.equalsIgnoreCase("false")) {
                gwd.setStatus("OFFILINE");

            }
            gwd.setProtocolo(String.valueOf(gateway.getProtocol()));

        } catch (TimeoutException | GatewayException | IOException | InterruptedException ex) {
            Logger.getLogger(ControllerSIM.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            new BaseRepository<GatewayModem>(GatewayModem.class).save(gwd);

        } catch (PersistenceException ex) {
            Logger.getLogger(ControllerSIM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
