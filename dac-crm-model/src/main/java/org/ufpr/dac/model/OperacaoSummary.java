package org.ufpr.dac.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OperacaoSummary implements Summary, Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private double valorTotal;
	private TipoOperacao tipoOperacao;
	private NotaFiscalSummary notaFiscal = new NotaFiscalSummary();
	private Date dataOperacao;
	
	public OperacaoSummary(){
		super();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public TipoOperacao getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(TipoOperacao tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public NotaFiscalSummary getNotaFiscal() {
		return notaFiscal;
	}
	public void setNotaFiscal(NotaFiscalSummary notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public Date getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(Date dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataOperacao == null) ? 0 : dataOperacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((notaFiscal == null) ? 0 : notaFiscal.hashCode());
		result = prime * result
				+ ((tipoOperacao == null) ? 0 : tipoOperacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperacaoSummary other = (OperacaoSummary) obj;
		if (dataOperacao == null) {
			if (other.dataOperacao != null)
				return false;
		} else if (!dataOperacao.equals(other.dataOperacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notaFiscal == null) {
			if (other.notaFiscal != null)
				return false;
		} else if (!notaFiscal.equals(other.notaFiscal))
			return false;
		if (tipoOperacao != other.tipoOperacao)
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double
				.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}

}
