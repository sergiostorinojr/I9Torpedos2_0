/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "SMSReceivedSim5")
public class ReceivedSIM5 extends SMSReceived implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date receiptDate;
    @Temporal(TemporalType.TIME)
    private Date receiptHour;

    public ReceivedSIM5() {
    }

    public ReceivedSIM5(Date receiptDate, Date receiptHour) {
        this.receiptDate = receiptDate;
        this.receiptHour = receiptHour;
    }

    /**
     * @return the receiptDate
     */
    public Date getReceiptDate() {
        return receiptDate;
    }

    /**
     * @param receiptDate the receiptDate to set
     */
    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    /**
     * @return the receiptHour
     */
    public Date getReceiptHour() {
        return receiptHour;
    }

    /**
     * @param receiptHour the receiptHour to set
     */
    public void setReceiptHour(Date receiptHour) {
        this.receiptHour = receiptHour;
    }

}
