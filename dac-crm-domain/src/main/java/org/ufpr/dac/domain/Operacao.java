package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the operacao database table.
 * 
 */
@Entity
@NamedQuery(name="Operacao.findAll", query="SELECT o FROM Operacao o")
public class Operacao implements Domain, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="tipo_operacao")
	@NotNull(message="org.ufpr.dac.tipoOperacaoCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.tipoOperacaoCannotBeNull")
	private String tipoOperacao;

	@Column(name="valor_total")
	@NotNull(message="org.ufpr.dac.valorTotalCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.valorTotalCannotBeNull")
	private double valorTotal;

	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="nf_id")
	@NotNull(message="org.ufpr.dac.nfOperacaCannotBeNull")
	@NotEmpty(message="org.ufpr.dac.nfOperacaCannotBeNull")
	private NotaFiscal notaFiscal;

	public Operacao() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoOperacao() {
		return this.tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public double getValorTotal() {
		return this.valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public NotaFiscal getNotaFiscal() {
		return this.notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

}