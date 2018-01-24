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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author esmayk
 */
@Entity
@Table(name = "cidade", schema = "public")
@SequenceGenerator(name = "seq_cidade", sequenceName = "public.seq_cidade", initialValue = 1, allocationSize = 1)
public class Cidade implements I9ArCondicionadoModel {

    private static final long serialVersionUID = 4259166983258707188L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_cidade")
    @Basic(optional = false)
    @Column(name = "cidade_pk")
    private BigInteger id;

    @Size(max = 80)
    @Column(name = "nome")
    private String nome;

    @JoinColumn(name = "estado_fk", referencedColumnName = "estado_pk")
    @ManyToOne(optional = false)
    private Estado estadoFk;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cidadeFk")
    private List<Endereco> enderecoList;

    public Cidade() {
    }

    public Cidade(BigInteger id) {
        this.id = id;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstadoFk() {
        return estadoFk;
    }

    public void setEstadoFk(Estado estadoFk) {
        this.estadoFk = estadoFk;
    }

    @XmlTransient
    public List<Endereco> getEnderecoList() {
        return enderecoList;
    }

    public void setEnderecoList(List<Endereco> enderecoList) {
        this.enderecoList = enderecoList;
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
        if (!(object instanceof Cidade)) {
            return false;
        }
        Cidade other = (Cidade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.i9arcondicionado.entidade.Cidade[ id=" + id + " ]";
    }

}
