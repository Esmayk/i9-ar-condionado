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
import java.text.SimpleDateFormat;
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
    private Estado estado;
    private Cidade cidade;
    private Endereco endereco;

    private Pessoa pessoaSelecionada;
    private List<Pessoa> pessoas = new ArrayList<>();
    private List<Estado> estados;
    private List<Cidade> cidades;

    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public PessoaBean() {
        pessoa = new Pessoa();
        endereco = new Endereco();
    }

    public void salvar() {
        try {
            endereco = new Endereco();
            //cidade = cidadeFacade.listar();
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
        return "pessoa";
    }

    public String voltar() {
        return "pessoa";
    }

    public void popular() {
        if (estado != null) {
            cidades = (List<Cidade>) cidadeFacade.listar(estado.getId());
            estados = estadoFacade.listar();
        } else {
            cidades = new ArrayList<>();
        }
    }

    @PostConstruct
    public void listar() {
        try {
            List<Pessoa> lista = pessoaFacade.listar();
            pessoas = listaPessoasFormatada(lista);
        } catch (RuntimeException erro) {
            FacesMessages.error("Ocorreu um erro ao tentar listar os pessoas");
            erro.printStackTrace();
        }

    }

    public void remover() {
        try {
            pessoaFacade.remover(pessoaSelecionada);
            pessoas.clear();
            List<Pessoa> lista = pessoaFacade.listar();
            pessoas = listaPessoasFormatada(lista);
            FacesMessages.info("Pessoa removido com sucesso.");
        } catch (RuntimeException erro) {
            FacesMessages.error("Ocorreu um erro ao tentar remover pessoa.");
            erro.getMessage();
        }
    }

    public void onSelect(Pessoa pessoa, String typeOfSelection, String indexes) {
        if (null != pessoa) {
            setPessoaSelecionada(pessoa);
        } else if (null != indexes) {
            String[] indexArray = indexes.split(",");
            for (String index : indexArray) {
                int i = Integer.valueOf(index);
                Pessoa p = pessoas.get(i);
                if (!pessoas.contains(p)) {
                    setPessoaSelecionada(p);
                }
            }
        }
    }

    private List<Pessoa> listaPessoasFormatada(List<Pessoa> lista) {
        for (Pessoa pes : lista) {
            pes.setDtNas(format.format(pes.getNascimento()));
            pessoas.add(pes);
        }
        return pessoas;
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
