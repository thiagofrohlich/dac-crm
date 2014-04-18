package org.ufpr.dac.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.ufpr.dac.model.ProdutoSummary;
@ViewScoped
@ManagedBean(name= "produtoBean")
public class ProdutoBean {

	private ProdutoSummary produto = new ProdutoSummary();
	private List<ProdutoSummary> lstProd = new ArrayList<>();
	private ResourceBundle rb = ResourceBundle.getBundle("resources/messages.properties");
	public boolean validaCadastro(){
		boolean ret = true;
		if(produto.getDescricao() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "ERRO", rb.getString("erroDescricao")));
			ret = false;
		}
		if(produto.getFornecedor().getRootId() == null || produto.getFornecedor().getRootId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "ERRO", rb.getString("erroFornecedor")));
			ret = false;
		}
		if(produto.getQtdEstoque() == null || produto.getQtdEstoque() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "ERRO", rb.getString("erroQtdEstoque")));
			ret = false;
		}
		if(produto.getValorCompra()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "ERRO", rb.getString("erroVlrCompra")));
			ret = false;
		}
		if(produto.getValorVenda() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "ERRO", rb.getString("erroVlrVenda")));
			ret = false;
		}
		return ret;
	}

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
