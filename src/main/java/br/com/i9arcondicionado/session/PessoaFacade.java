/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.session;

import br.com.i9arcondicionado.entidade.Pessoa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author esmayktillesse
 */
@Stateless
public class PessoaFacade extends AbstractFacade<Pessoa> {
    
    @PersistenceContext(unitName = "I9ArcondicionadoPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PessoaFacade() {
        super(Pessoa.class);
    }
    
    public void merge(Pessoa pessoa) {
        if (pessoa.getId() == null) {
            super.create(pessoa);
        } else {
            super.edit(pessoa);
        }
    }
    
    public void remover(Pessoa pessoa) {
        String update = "update Pessoa set status = 'I' where id =:id";
        Query query = getEntityManager().createQuery(update);
        query.setParameter("id", pessoa.getId());
        query.executeUpdate();
    }
    
    public List<Pessoa> listar() {
        String consulta = "From Pessoa Where status = 'A'";
        Query query = getEntityManager().createQuery(consulta);
        List<Pessoa> lista = query.getResultList();
        return lista;
    }
}
