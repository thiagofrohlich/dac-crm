package org.ufpr.dac.bean;

import javax.faces.bean.ManagedBean;

import org.ufpr.dac.model.ProdutoSummary;

@ManagedBean(name= "produtoBean")
public class ProdutoBean {

	private ProdutoSummary produto;

	public ProdutoSummary getProduto() {
		return produto;
	}

	public void setProduto(ProdutoSummary produto) {
		this.produto = produto;
	}
	
}
