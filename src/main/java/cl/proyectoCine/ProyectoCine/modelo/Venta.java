/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.proyectoCine.ProyectoCine.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ricardo
 */
@Entity
@Table(name = "venta")
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nro_boleta")
    private Integer nroBoleta;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private int total;
    @JoinColumn(name = "Usuarioid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuario usuarioid;
    @JoinColumn(name = "funcion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Funcion funcion;

    public Venta() {
    }

    public Venta(Integer nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    public Venta(Integer nroBoleta, String usuario, int total) {
        this.nroBoleta = nroBoleta;
        this.usuario = usuario;
        this.total = total;
    }

    public Integer getNroBoleta() {
        return nroBoleta;
    }

    public void setNroBoleta(Integer nroBoleta) {
        this.nroBoleta = nroBoleta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Usuario getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(Usuario usuarioid) {
        this.usuarioid = usuarioid;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroBoleta != null ? nroBoleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.nroBoleta == null && other.nroBoleta != null) || (this.nroBoleta != null && !this.nroBoleta.equals(other.nroBoleta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cl.proyectoCine.ProyectoCine.modelo.Venta[ nroBoleta=" + nroBoleta + " ]";
    }
    
}
