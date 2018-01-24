/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.entidade;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author esmayk
 */
@Entity
@Table(name = "perfil", schema = "public")
@SequenceGenerator(name = "seq_perfil", sequenceName = "public.seq_perfil", initialValue = 1, allocationSize = 1)
public class Perfil implements I9ArCondicionadoModel {

    private static final long serialVersionUID = -9051228439514658024L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_perfil")
    @Basic(optional = false)
    @Column(name = "perfil_pk")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "descricao")
    private String descricao;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfilFk")
    private List<Usuario> usuarioList;

    public Perfil() {
    }

    public Perfil(BigInteger id) {
        this.id = id;
    }

    public Perfil(BigInteger id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.i9arcondicionado.entidade.Perfil[ id=" + id + " ]";
    }
    
}
