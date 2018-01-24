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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author esmayk
 */
@Entity
@Table(name = "endereco", schema = "public")
@SequenceGenerator(name = "seq_endereco", sequenceName = "public.seq_endereco", initialValue = 1, allocationSize = 1)
public class Endereco implements I9ArCondicionadoModel {

    private static final long serialVersionUID = -5508039912455548319L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_endereco")
    @Basic(optional = false)
    @Column(name = "endereco_pk")
    private BigInteger id;

    @Size(max = 100)
    @Column(name = "end_descricao")
    private String endDescricao;

    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;

    @Size(max = 40)
    @Column(name = "complemento")
    private String complemento;

    @Size(max = 80)
    @Column(name = "bairro")
    private String bairro;

    @Size(max = 8)
    @Column(name = "cep")
    private String cep;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enderecoFk")
    private List<Pessoa> pessoaList;

    @JoinColumn(name = "cidade_fk", referencedColumnName = "cidade_pk")
    @ManyToOne(optional = false)
    private Cidade cidadeFk;

    public Endereco() {
    }

    public Endereco(BigInteger id) {
        this.id = id;
    }

    public Endereco(BigInteger id, int numero) {
        this.id = id;
        this.numero = numero;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEndDescricao() {
        return endDescricao;
    }

    public void setEndDescricao(String endDescricao) {
        this.endDescricao = endDescricao;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @XmlTransient
    public List<Pessoa> getPessoaList() {
        return pessoaList;
    }

    public void setPessoaList(List<Pessoa> pessoaList) {
        this.pessoaList = pessoaList;
    }

    public Cidade getCidadeFk() {
        return cidadeFk;
    }

    public void setCidadeFk(Cidade cidadeFk) {
        this.cidadeFk = cidadeFk;
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
        if (!(object instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.i9arcondicionado.entidade.Endereco[ id=" + id + " ]";
    }
    
}
