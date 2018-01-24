/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.bean;

import br.com.i9arcondicionado.entidade.Usuario;
import br.com.i9arcondicionado.session.UsuarioFacade;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author esmayktillesse
 */
@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {

    private static final long serialVersionUID = 8668130846315831908L;
    
    @EJB
    UsuarioFacade usuarioFacade;

    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public UsuarioBean() {
     usuario = new Usuario();
    }
     public String envia() {
        usuario = usuarioFacade.getUsuario(usuario.getLogin(), usuario.getSenha());
        if (usuario == null) {
            usuario = new Usuario();
            FacesMessages.error("Usuário não encontrado!","Erro no Login!");
            return null;
        } else {
            FacesMessages.info("Bem-Vindo!",usuario.getLogin());
            return "principal";
        }
     }
}
