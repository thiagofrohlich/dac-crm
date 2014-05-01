package org.ufpr.dac.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.model.ProdutoSummary;
@ViewScoped
@ManagedBean(name="relatorioBean")
public class RelatorioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tipoPesquisa = 1;
	private Date dataini;
	private Date datafim;
	private PessoaSummary pessoa = new PessoaJuridicaSummary();
	private ProdutoSummary produto = new ProdutoSummary();
	private List<OperacaoSummary> lstOperacao = new ArrayList<>();
	private String doc;
	
	
	public void escolhePessoa(){
		if(tipoPesquisa == 1){
			pessoa = new PessoaFisicaSummary();
		}else{
			pessoa = new PessoaJuridicaSummary();
		}
	}
	
		
	
	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	
	
	public Date getDataini() {
		return dataini;
	}
	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}
	public Date getDatafim() {
		return datafim;
	}
	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}
	public PessoaSummary getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaSummary pessoa) {
		this.pessoa = pessoa;
	}
	public ProdutoSummary getProduto() {
		return produto;
	}
	public void setProduto(ProdutoSummary produto) {
		this.produto = produto;
	}
	public List<OperacaoSummary> getLstOperacao() {
		return lstOperacao;
	}
	public void setLstOperacao(List<OperacaoSummary> lstOperacao) {
		this.lstOperacao = lstOperacao;
	}



	public String getDoc() {
		return doc;
	}



	public void setDoc(String doc) {
		this.doc = doc;
	}
}
