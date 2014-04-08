package org.ufpr.dac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.ufpr.dac.model.ProdutoSummary;

@ManagedBean(name= "produtoBean")
public class ProdutoBean {

	private ProdutoSummary produto;
	private List<ProdutoSummary> lstProd = new ArrayList<>();

	public ProdutoSummary getProduto() {
		return produto;
	}

	public void setProduto(ProdutoSummary produto) {
		this.produto = produto;
	}

	public List<ProdutoSummary> getLstProd() {
		return lstProd;
	}

	public void setLstProd(List<ProdutoSummary> lstProd) {
		this.lstProd = lstProd;
	}
	
}
