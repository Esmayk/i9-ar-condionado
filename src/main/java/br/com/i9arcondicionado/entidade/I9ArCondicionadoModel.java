/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.entidade;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author esmayktillesse
 */
public interface I9ArCondicionadoModel extends Serializable {
    /**
     * Retorna o id da entidade
     * @return integer
     */
    BigInteger getId();

    /**
     * Seta o id da entidade
     * @param id Primary key
     */
    void setId(BigInteger id);

    /**
     * Retorna um código para identificação do objeto
     * @return int
     */
    @Override
    int hashCode();

    /**
     * Verifica se este objeto é igual ao objeto passado
     * @param object Objeto
     * @return boolean
     */
    @Override
    boolean equals(Object object);
}
