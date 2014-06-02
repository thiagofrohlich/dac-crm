package org.ufpr.dac.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the produto_nf database table.
 * 
 */
@Embeddable
public class ProdutoNfPK implements Domain, Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	
	@Column(name="produto_id")
	private Long produtoId;

	@JoinColumn(name="nf_id" , referencedColumnName="id")
	@Column(name="nf_id")
	private Long notaFiscal;

	public ProdutoNfPK() {
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((notaFiscal == null) ? 0 : notaFiscal.hashCode());
		result = prime * result
				+ ((produtoId == null) ? 0 : produtoId.hashCode());
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
		ProdutoNfPK other = (ProdutoNfPK) obj;
		if (notaFiscal == null) {
			if (other.notaFiscal != null)
				return false;
		} else if (!notaFiscal.equals(other.notaFiscal))
			return false;
		if (produtoId == null) {
			if (other.produtoId != null)
				return false;
		} else if (!produtoId.equals(other.produtoId))
			return false;
		return true;
	}
	public void setNotaFiscal(Long notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	public Long getNotaFiscal() {
		return notaFiscal;
	}


	public Long getProdutoId() {
		return produtoId;
	}


	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	
}