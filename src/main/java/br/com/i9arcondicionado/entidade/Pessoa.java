/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.entidade;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author esmayktillesse
 */
@Entity
@Table(name = "pessoa", schema = "public")
@SequenceGenerator(name = "seq_pessoa", sequenceName = "public.seq_pessoa", initialValue = 1, allocationSize = 1)
public class Pessoa implements I9ArCondicionadoModel {

    private static final long serialVersionUID = -2803600217199626683L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_pessoa")
    @Basic(optional = false)
    @NotNull
    @Column(name = "pessoa_pk")
    private BigInteger id;

    @Size(max = 100)
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @Size(min = 1, max = 11)
    @Column(name = "cpf")
    private String cpf;

    @Size(max = 1)
    @Column(name = "estado_civil")
    private String estadoCivil;

    @Size(max = 1)
    @Column(name = "sexo")
    private String sexo;

    @Column(name = "nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;

    @Size(max = 100)
    @Column(name = "mae")
    private String mae;

    @Size(max = 100)
    @Column(name = "pai")
    private String pai;

    @Size(max = 11)
    @Column(name = "telefone")
    private String telefone;

    @Size(max = 11)
    @Column(name = "celular")
    private String celular;

    @Basic(optional = false)
    @Column(name = "data_cadastro")
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;

    @Column(name = "status")
    private Character status;

    @Transient
    private String dtNas;

    @JoinColumn(name = "endereco_fk", referencedColumnName = "endereco_pk")
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Endereco enderecoFk;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoaFk")
    private List<Manutencao> manutencaoList;

    public Pessoa() {
    }

    public Pessoa(BigInteger id) {
        this.id = id;
    }

    public Pessoa(BigInteger id, String cpf, Date dataCadastro) {
        this.id = id;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Endereco getEnderecoFk() {
        return enderecoFk;
    }

    public void setEnderecoFk(Endereco enderecoFk) {
        this.enderecoFk = enderecoFk;
    }

    @XmlTransient
    public List<Manutencao> getManutencaoList() {
        return manutencaoList;
    }

    public void setManutencaoList(List<Manutencao> manutencaoList) {
        this.manutencaoList = manutencaoList;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getDtNas() {
        return dtNas;
    }

    public void setDtNas(String dtNas) {
        this.dtNas = dtNas;
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
        if (!(object instanceof Pessoa)) {
            return false;
        }
        Pessoa other = (Pessoa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.i9arcondicionado.entidade.Pessoa[ id=" + id + " ]";
    }
}
