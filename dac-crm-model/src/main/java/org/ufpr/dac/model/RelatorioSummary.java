package org.ufpr.dac.model;

import java.util.Date;

public class RelatorioSummary implements Summary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date data_operacao;
	private String cpf;
	private String cnpj;
	private String doc;
	private String nome;
	private Double valor_total;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData_operacao() {
		return data_operacao;
	}
	public void setData_operacao(Date data_operacao) {
		this.data_operacao = data_operacao;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getValor_total() {
		return valor_total;
	}
	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}

}
