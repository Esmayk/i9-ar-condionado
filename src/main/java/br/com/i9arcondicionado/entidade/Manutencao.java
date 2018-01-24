/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.entidade;

import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author esmayk
 */
@Entity
@Table(name = "manutencao", schema = "public")
@SequenceGenerator(name = "seq_manutencao", sequenceName = "public.seq_manutencao", initialValue = 1, allocationSize = 1)
public class Manutencao implements I9ArCondicionadoModel {

    private static final long serialVersionUID = 3198514096260585566L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_manutencao")
    @Basic(optional = false)
    @Column(name = "manutencao_pk")
    private BigInteger id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "primeira_manutecao")
    @Temporal(TemporalType.DATE)
    private Date primeiraManutecao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "proxima_manutecao")
    @Temporal(TemporalType.DATE)
    private Date proximaManutecao;
    
    @JoinColumn(name = "pessoa_fk", referencedColumnName = "pessoa_pk")
    @ManyToOne(optional = false)
    private Pessoa pessoaFk;

    public Manutencao() {
    }

    public Manutencao(BigInteger id) {
        this.id = id;
    }

    public Manutencao(BigInteger id, Date primeiraManutecao, Date proximaManutecao) {
        this.id = id;
        this.primeiraManutecao = primeiraManutecao;
        this.proximaManutecao = proximaManutecao;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getPrimeiraManutecao() {
        return primeiraManutecao;
    }

    public void setPrimeiraManutecao(Date primeiraManutecao) {
        this.primeiraManutecao = primeiraManutecao;
    }

    public Date getProximaManutecao() {
        return proximaManutecao;
    }

    public void setProximaManutecao(Date proximaManutecao) {
        this.proximaManutecao = proximaManutecao;
    }

    public Pessoa getPessoaFk() {
        return pessoaFk;
    }

    public void setPessoaFk(Pessoa pessoaFk) {
        this.pessoaFk = pessoaFk;
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
        if (!(object instanceof Manutencao)) {
            return false;
        }
        Manutencao other = (Manutencao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.i9arcondicionado.entidade.Manutecao[ id=" + id + " ]";
    }
    
}
