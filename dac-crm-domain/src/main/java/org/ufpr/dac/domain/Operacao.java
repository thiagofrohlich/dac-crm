package org.ufpr.dac.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the operacao database table.
 * 
 */
@Entity
@NamedQuery(name="Operacao.findAll", query="SELECT o FROM Operacao o")
public class Operacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="tipo_operacao")
	private String tipoOperacao;

	@Column(name="valor_total")
	private double valorTotal;

	//bi-directional many-to-one association to NotaFiscal
	@ManyToOne
	@JoinColumn(name="nf_id")
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