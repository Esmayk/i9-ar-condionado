/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.session;

import br.com.i9arcondicionado.entidade.Usuario;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author esmayktillesse
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "I9ArcondicionadoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario getUsuario(String login, String senha) {
        senha = md5(senha);
        try {
            Usuario usuario = (Usuario) em
                    .createQuery(
                            "SELECT u from Usuario u where u.login = :name and u.senha = :senha")
                    .setParameter("name", login)
                    .setParameter("senha", senha).getSingleResult();
            if (usuario.getAtivo() == true) {
                usuario.setUltimoAcesso(new Date());
                em.merge(usuario);
                return usuario;
            } else {
                return null;
            }
            
        } catch (NoResultException e) {
            return null;
        }
    }

    public static String md5(String input) {
        String md5 = null;
        if (null == input) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes("UTF-8"), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            FacesMessages.error("Erro ao criptografar senha " + e.getMessage());
        }
        return md5;
    }

}
