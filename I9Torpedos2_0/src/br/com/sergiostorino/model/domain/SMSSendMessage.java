/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sergiostorino.model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Junior
 */
@Entity
@Table(name = "SMSSendMessage")
@SequenceGenerator(name = "SMSSendMessage_SEQ", sequenceName = "SMSSendMessage_SEQ", allocationSize = 1, initialValue = 1)
public class SMSSendMessage implements Serializable {

    private static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SMSSendMessage_SEQ")
    private Long id;
    @Column(name = "numero", length = 40)
    private String numero;
    @Column(name = "mensagem", length = 2000)
    private String mensagem;

    @Temporal(TemporalType.DATE)
    private Date receiptDate;
    @Temporal(TemporalType.TIME)
    private Date receiptHour;

    public SMSSendMessage() {
    }

    public SMSSendMessage(Long id, String numero, String mensagem, Date receiptDate, Date receiptHour) {
        this.id = id;
        this.numero = numero;
        this.mensagem = mensagem;
        this.receiptDate = receiptDate;
        this.receiptHour = receiptHour;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
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
