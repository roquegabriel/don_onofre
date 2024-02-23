
package com.don.onofre.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author roque
 */
@Entity
@Table(name = "deudas")
public class Deudas implements Serializable {
     private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdamsDocId() {
        return adamsDocId;
    }

    public void setAdamsDocId(Integer adamsDocId) {
        this.adamsDocId = adamsDocId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private Integer id;

    @Column(name = "adams_doc_id")
    private Integer adamsDocId;

    @Column(name = "pay_time")
    private Date payTime;

    @ManyToOne
    @Cascade(CascadeType.MERGE)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes cliente;
   

}
