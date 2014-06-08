package org.ufpr.dac.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProdutoNfSummary implements Summary {

	private static final long serialVersionUID = 1L;
	
	private Long nfId;
	private Long produtoId;
	private Double quantidade;
	
	public Long getNfId() {
		return nfId;
	}
	public void setNfId(Long nfId) {
		this.nfId = nfId;
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	
	public void somaQtd(Double qtd){
		this.quantidade = this.quantidade + qtd;
	}
	public void subtraiQtd(Double qtd){
		this.quantidade = this.quantidade-qtd;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nfId == null) ? 0 : nfId.hashCode());
		result = prime * result
				+ ((produtoId == null) ? 0 : produtoId.hashCode());
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
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
		ProdutoNfSummary other = (ProdutoNfSummary) obj;
		if (nfId == null) {
			if (other.nfId != null)
				return false;
		} else if (!nfId.equals(other.nfId))
			return false;
		if (produtoId == null) {
			if (other.produtoId != null)
				return false;
		} else if (!produtoId.equals(other.produtoId))
			return false;
		return true;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	
}
