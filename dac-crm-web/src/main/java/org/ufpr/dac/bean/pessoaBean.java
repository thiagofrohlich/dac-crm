package org.ufpr.dac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class pessoaBean {
	
	private PessoaSummary pessoa;
	private Integer tipoPessoa = 1;
	private List<PessoaSummary> lstPessoa = new ArrayList<>();
	
	
	public void escolhePessoa(){
		if(tipoPessoa == 1){
			pessoa = new PessoaFisicaSummary();
		}else{
			pessoa = new PessoaJuridicaSummary();
		}
	}
	
	public void validaCadastro(){
		boolean ret = true;
		if(pessoa.getNome() != null){
			
		}
	}
	
	
	
	public PessoaSummary getPessoa() {
		return pessoa;
	}
	public void setPessoa(PessoaSummary pessoa) {
		this.pessoa = pessoa;
	}
	public Integer getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}



	public List<PessoaSummary> getLstPessoa() {
		return lstPessoa;
	}



	public void setLstPessoa(List<PessoaSummary> lstPessoa) {
		this.lstPessoa = lstPessoa;
	}


	
	
	
}
