/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.session;

import br.com.i9arcondicionado.entidade.Cidade;
import br.com.i9arcondicionado.entidade.Pessoa;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 *
 * @author esmayk
 */
@Stateless
public class CidadeFacade extends AbstractFacade<Cidade>{

    @PersistenceContext(unitName = "I9ArcondicionadoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CidadeFacade() {
        super(Cidade.class);
    }
    
    public List<Cidade> listar(BigInteger estado) {
        String consulta = "FROM Cidade WHERE estadoFk ="+ estado;
        Query query = getEntityManager().createQuery(consulta);
        List<Cidade> cidades = (List<Cidade>) query.getResultList();
        return cidades;
    }
}
