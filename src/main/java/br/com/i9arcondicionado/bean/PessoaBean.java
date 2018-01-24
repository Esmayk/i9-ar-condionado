/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.i9arcondicionado.bean;

import br.com.i9arcondicionado.entidade.Cidade;
import br.com.i9arcondicionado.entidade.Endereco;
import br.com.i9arcondicionado.entidade.Estado;
import br.com.i9arcondicionado.entidade.Pessoa;
import br.com.i9arcondicionado.session.CidadeFacade;
import br.com.i9arcondicionado.session.EstadoFacade;
import br.com.i9arcondicionado.session.PessoaFacade;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author esmayk.tillesse
 */
@ManagedBean
@SessionScoped
public class PessoaBean implements Serializable {

    private static final long serialVersionUID = -99355507489343788L;

    @EJB
    PessoaFacade pessoaFacade;
    @EJB
    EstadoFacade estadoFacade;
    @EJB
    CidadeFacade cidadeFacade;

    private Pessoa pessoa;
    private List<Pessoa> pessoas;
    private List<Estado> estados;
    private List<Cidade> cidades;
    private Estado estado;
    private Cidade cidade;
    private Endereco endereco;
    private Pessoa pessoaSelecionada;

    public PessoaBean() {
        pessoa = new Pessoa();
        endereco = new Endereco();

    }

    public void salvar() {
        try {
            cidade = cidadeFacade.listar(BigInteger.valueOf(6L));
            endereco.setCidadeFk(cidade);
            pessoa.setEnderecoFk(endereco);
            pessoa.setDataCadastro(new Date());
            pessoaFacade.inserir(pessoa);
            pessoa = new Pessoa();
            FacesMessages.info("Pessoa salvo com sucesso.");
        } catch (RuntimeException erro) {
            FacesMessages.error("Ocorreu um erro ao tentar gerar um novo pessoa");
            erro.printStackTrace();
        }

    }

    public String adicionar() {
        estados = estadoFacade.listar();
        return "cad_pessoa";
    }

    public String voltar() {
        return "list_pessoa";
    }

    public void popular() {
        if (estado != null) {
            cidades = (List<Cidade>) cidadeFacade.listar(new BigInteger("6"));
            estados = estadoFacade.listar();
        } else {
            cidades = new ArrayList<>();
        }
    }

    @PostConstruct
    public void listar() {
        try {
            pessoas = pessoaFacade.listar();
        } catch (RuntimeException erro) {
            FacesMessages.error("Ocorreu um erro ao tentar listar os pessoas");
            erro.printStackTrace();
        }

    }

    public void remover() {
        try {
            pessoaFacade.remover(pessoaSelecionada);
            pessoas = pessoaFacade.listar();
            FacesMessages.info("Pessoa removido com sucesso.");
        } catch (RuntimeException erro) {
            FacesMessages.error("Ocorreu um erro ao tentar remover pessoa.");
            erro.printStackTrace();
        }
    }

    public void onSelect(Pessoa pessoa, String typeOfSelection, String indexes) {
        System.out.println("OnSelect:" + pessoa + " typeOfSelection: " + typeOfSelection + " indexes: " + indexes);
        if (null != pessoa) {
            setPessoaSelecionada(pessoa);
        } else if (null != indexes) {
            String[] indexArray = indexes.split(",");
            for (String index : indexArray) {
                int i = Integer.valueOf(index);
                Pessoa p = pessoas.get(i);
//                if (!pessoaSelecionada.equals(p.toString()))) {
//                    setPessoaSelecionada(p);
//                }
            }
        }
    }

    public PessoaFacade getPessoaFacade() {
        return pessoaFacade;
    }

    public void setPessoaFacade(PessoaFacade pessoaFacade) {
        this.pessoaFacade = pessoaFacade;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoaList(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pessoa getPessoaSelecionada() {
        return pessoaSelecionada;
    }

    public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
        this.pessoaSelecionada = pessoaSelecionada;
    }

}
