

package com.don.onofre.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author roque
 */
@Entity
@Table(name = "clientes")
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;

    public Clientes() {
    }

    public Clientes(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDoc() {
        return doc;
    }

    public void setDoc(Integer doc) {
        this.doc = doc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "Clientes{" + "id=" + id + ", nombre=" + nombre + ", doc=" + doc + ", email=" + email + ", payTime=" + payTime + '}';
    }

    public Set<Deudas> getDeudas() {
        return deudas;
    }

    public void setDeudas(Set<Deudas> deudas) {
        this.deudas = deudas;
    }

    public void addDeudas(Deudas deuda) {
        this.deudas.add(deuda);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "doc")
    private Integer doc;
    @Column(name = "email")
    private String email;
    @Column(name = "pay_time")
    private Date payTime;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Deudas> deudas;

}
