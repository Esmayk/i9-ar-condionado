/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.converter;

import br.com.i9arcondicionado.entidade.Cidade;
import br.com.i9arcondicionado.session.CidadeFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author esmayk
 */
@FacesConverter(value = "converterCidade")
public class ConverterCidade implements Converter {

    @EJB
    CidadeFacade cidadeFacade;
    private List<Cidade> cidades;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value != null && !value.equals("")) {
                //cidades = cidadeFacade.listar(new BigInteger(value));
                return cidades;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Cidade) {
            Cidade cidade = (Cidade) value;
            return String.valueOf(cidade.getId());
        }
        return "";
    }

}
