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
import javax.validation.constraints.Size;

/**
 *
 * @author esmayk
 */
@Entity
@Table(name = "usuario", schema = "public")
@SequenceGenerator(name = "seq_usuario", sequenceName = "public.seq_usuario", initialValue = 1, allocationSize = 1)
public class Usuario implements I9ArCondicionadoModel {

    private static final long serialVersionUID = -6222370107096546481L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usuario")
    @Basic(optional = false)
    @Column(name = "usuario_pk")
    private BigInteger id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "login")
    private String login;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "senha")
    private String senha;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ativo")
    private boolean ativo;

    @Column(name = "utimo_acesso", unique = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcesso;

    @JoinColumn(name = "perfil_fk", referencedColumnName = "perfil_pk")
    @ManyToOne(optional = false)
    private Perfil perfilFk;

    public Usuario() {
    }

    public Usuario(BigInteger id) {
        this.id = id;
    }

    public Usuario(BigInteger id, String login, String senha, boolean ativo, Date ultimoAcesso) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.ultimoAcesso = ultimoAcesso;
    }

    @Override
    public BigInteger getId() {
        return id;
    }

    @Override
    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
    
    public Perfil getPerfilFk() {
        return perfilFk;
    }

    public void setPerfilFk(Perfil perfilFk) {
        this.perfilFk = perfilFk;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.i9arcondicionado.entidade.Usuario[ id=" + id + " ]";
    }

}
