package org.ufpr.dac.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the produto_nf database table.
 * 
 */
@Embeddable
public class ProdutoNfPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="produto_id", insertable=false, updatable=false)
	private Long produtoId;

	@Column(name="nf_id", insertable=false, updatable=false)
	private Long nfId;

	public ProdutoNfPK() {
	}
	public Long getProdutoId() {
		return this.produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	public Long getNfId() {
		return this.nfId;
	}
	public void setNfId(Long nfId) {
		this.nfId = nfId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProdutoNfPK)) {
			return false;
		}
		ProdutoNfPK castOther = (ProdutoNfPK)other;
		return 
			this.produtoId.equals(castOther.produtoId)
			&& this.nfId.equals(castOther.nfId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.produtoId.hashCode();
		hash = hash * prime + this.nfId.hashCode();
		
		return hash;
	}
}