package org.ufpr.dac.bean;

import javax.faces.bean.ManagedBean;

import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;


@ManagedBean(name = "pessoaBean")
public class pessoaBean {
	
	private PessoaSummary pessoa;
	private Integer tipoPessoa = 1;
	
	
	
	public void escolhePessoa(){
		if(tipoPessoa == 1){
			pessoa = new PessoaFisicaSummary();
		}else{
			pessoa = new PessoaJuridicaSummary();
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


	
	
	
}
