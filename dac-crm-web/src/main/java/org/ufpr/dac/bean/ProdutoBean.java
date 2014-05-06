package org.ufpr.dac.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.service.PessoaServiceHandler;
import org.ufpr.dac.service.ProdutoServiceHandler;
@ViewScoped
@ManagedBean(name= "produtoBean")
public class ProdutoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProdutoServiceHandler produtoService = new ProdutoServiceHandler();
	private PessoaServiceHandler pessoaService = new PessoaServiceHandler();
	private ProdutoSummary produto = new ProdutoSummary();
	private ProdutoSummary prodDialog = new ProdutoSummary();
	private List<ProdutoSummary> lstProd = new ArrayList<>();
	private ResourceBundle rb = ResourceBundle.getBundle("messages");
	
	public boolean validaCadastro(){
		boolean ret = true;
		if(produto.getDescricao() == null || produto.getDescricao().equals("")){
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroDescricao"));
			FacesContext.getCurrentInstance().addMessage(null, message);
			ret = false;
		}
		if(produto.getFornecedor().getCnpj() == null || produto.getFornecedor().getCnpj().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroFornecedor")));
			ret = false;
		}
		if(produto.getQtdEstoque() == null || produto.getQtdEstoque() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroQtdEstoque")));
			ret = false;
		}
		if(produto.getValorCompra()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroVlrCompra")));
			ret = false;
		}
		if(produto.getValorVenda() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroVlrVenda")));
			ret = false;
		}
		return ret;
	}
	
	public void salvar(){
		if(validaCadastro()){
			try{
				String cnpj = produto.getFornecedor().getCnpj().replace(".", "");
				cnpj = cnpj.replace("/", "");
				cnpj = cnpj.replace("-", "");
				produto.setFornecedor(pessoaService.getByCnpj(cnpj));
				produtoService.getAll(0);
//				produtoService.create(produto);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", "SUCESSO"));
			}catch(Exception e){
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroSalvarProd")));
			}
		}
	}

	public ProdutoSummary getProduto() {
		if(produto == null) produto = new ProdutoSummary();
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

	public ProdutoSummary getProdDialog() {
		return prodDialog;
	}

	public void setProdDialog(ProdutoSummary prodDialog) {
		this.prodDialog = prodDialog;
	}
	
}
