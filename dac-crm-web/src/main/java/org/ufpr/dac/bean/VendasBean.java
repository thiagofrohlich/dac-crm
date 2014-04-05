package org.ufpr.dac.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.ProdutoNfSummary;
import org.ufpr.dac.model.ProdutoSummary;

@ManagedBean(name = "vendasBean")
public class VendasBean {
	
	private OperacaoSummary operacao = new OperacaoSummary();
	private PessoaFisicaSummary cliente = new PessoaFisicaSummary();
	private ProdutoSummary produto = new ProdutoSummary();
	private List<ProdutoSummary> lstProdutos = new ArrayList<>();
	private Integer tipoPesquisa = 1;
	private BigDecimal subTotal = new BigDecimal(0);
	private BigDecimal acrescimos = new BigDecimal(0);
	private BigDecimal descontos = new BigDecimal(0);
	private Integer pagto;
	


	public void lancar(){
		ProdutoNfSummary produtoNf = new ProdutoNfSummary();
		produtoNf.setProdutoId(produto.getId());
		produtoNf.setQuantidade(produto.getQtd());
		operacao.getNotaFiscal().getProdutosNf().add(produtoNf);
		lstProdutos.add(produto);
		BigDecimal valor = new BigDecimal(produto.getValorVenda()*produto.getQtd());
		subTotal = subTotal.add(valor);
	}
	
	public void apagarProd(ProdutoSummary prod){
		lstProdutos.remove(prod);
		ProdutoNfSummary produtoNf = new ProdutoNfSummary();
		produtoNf.setProdutoId(produto.getId());
		produtoNf.setQuantidade(produto.getQtd());
		operacao.getNotaFiscal().getProdutosNf().remove(produtoNf);
		BigDecimal valor = new BigDecimal(produto.getValorVenda()*produto.getQtd());
		subTotal.subtract(valor);
	}
	
	public void somaAcrescimo(){
		operacao.setValorTotal(subTotal.add(acrescimos).doubleValue());
	}
	
	public void subtraiDesconto(){
		operacao.setValorTotal(subTotal.subtract(descontos).doubleValue());
	}
	
	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public OperacaoSummary getOperacao() {
		return operacao;
	}
	public void setOperacao(OperacaoSummary operacao) {
		this.operacao = operacao;
	}
	public PessoaFisicaSummary getCliente() {
		return cliente;
	}
	public void setCliente(PessoaFisicaSummary cliente) {
		this.cliente = cliente;
	}
	public ProdutoSummary getProduto() {
		return produto;
	}
	public void setProduto(ProdutoSummary produto) {
		this.produto = produto;
	}
	public List<ProdutoSummary> getLstProdutos() {
		return lstProdutos;
	}
	public void setLstProdutos(List<ProdutoSummary> lstProdutos) {
		this.lstProdutos = lstProdutos;
	}
	
	public Integer getPagto() {
		return pagto;
	}
	
	
	
	
	public void setPagto(Integer pagto) {
		this.pagto = pagto;
	}
	
	
	
	
	public BigDecimal getAcrescimos() {
		return acrescimos;
	}
	
	
	
	
	public void setAcrescimos(BigDecimal acrescimos) {
		this.acrescimos = acrescimos;
	}
	
	
	
	
	public BigDecimal getDescontos() {
		return descontos;
	}
	
	
	
	
	public void setDescontos(BigDecimal descontos) {
		this.descontos = descontos;
	}
	
	
	
	
	public BigDecimal getSubTotal() {
		return subTotal;
	}
	
	
	
	
	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}
}


