package org.ufpr.dac.bean;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.ProdutoNfSummary;
import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.model.TipoOperacao;
import org.ufpr.dac.service.OperacaoServiceHandler;
import org.ufpr.dac.service.PessoaServiceHandler;
import org.ufpr.dac.service.ProdutoServiceHandler;

import br.com.caelum.stella.format.CPFFormatter;
@ViewScoped
@ManagedBean(name = "vendasBean")
public class VendasBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PessoaServiceHandler pessoaService = new PessoaServiceHandler();
	private OperacaoServiceHandler operacaoService = new OperacaoServiceHandler();
	private ProdutoServiceHandler produtoService = new ProdutoServiceHandler();
	private OperacaoSummary operacao = new OperacaoSummary();
	private PessoaFisicaSummary cliente = new PessoaFisicaSummary();
	private ProdutoSummary produto = new ProdutoSummary();
	private List<ProdutoSummary> lstProdutos = new ArrayList<>();
	private Integer tipoPesquisa = 1;
	private BigDecimal subTotal = new BigDecimal(0);
	private BigDecimal acrescimos = new BigDecimal(0);
	private BigDecimal descontos = new BigDecimal(0);
	private Integer pagto;
	private ResourceBundle rb = ResourceBundle.getBundle("messages");


	public void lancar(){
		if(produto.getQtd() < produto.getEstoque()){
			ProdutoNfSummary produtoNf = new ProdutoNfSummary();
			produtoNf.setProdutoId(produto.getId());
			produtoNf.setQuantidade(produto.getQtd());
			operacao.getNotaFiscal().getProdutosNf().add(produtoNf);
			lstProdutos.add(produto);
			BigDecimal valor = new BigDecimal(produto.getValorVenda()*produto.getQtd());
			valor = valor.setScale(2, BigDecimal.ROUND_HALF_UP);
			subTotal = subTotal.add(valor);
			produto = new ProdutoSummary();
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroQtdEQtdEstoque")));
		}
	}
	
	public void apagarProd(ProdutoSummary prod){
		lstProdutos.remove(prod);
		ProdutoNfSummary produtoNf = new ProdutoNfSummary();
		produtoNf.setProdutoId(prod.getId());
		produtoNf.setQuantidade(prod.getQtd());
		operacao.getNotaFiscal().getProdutosNf().remove(produtoNf);
		BigDecimal valor = new BigDecimal(prod.getValorVenda()*prod.getQtd());
		valor = valor.setScale(2, BigDecimal.ROUND_HALF_UP);
		subTotal = subTotal.subtract(valor);
	}
	
	public void somaAcrescimo(){
		operacao.setValorTotal(subTotal.add(acrescimos).doubleValue());
	}
	
	public void subtraiDesconto(){
		operacao.setValorTotal(subTotal.subtract(descontos).doubleValue());
	}
	
	public boolean validaVenda(){
		boolean ret = true;
		if(cliente.getRootId() == null || cliente.getRootId() == 0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroFornecedor")));
			ret = false;
		}
		if(lstProdutos.isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroProd")));
			ret = false;
		}else{
			boolean erro = false;
			for(ProdutoSummary prod : lstProdutos){
				if(prod.getQtd() == null || prod.getQtd() == 0){
					erro = true;
				}
			}
			if(erro){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroQtdProd")));
				ret = false;
			}
		}
		return ret;
	}
	
	public Response salva(){
		if(validaVenda()){
			operacao.setTipoOperacao(TipoOperacao.VENDA);
			operacao.getNotaFiscal().setPessoa(cliente);
			operacao.setValorTotal(subTotal.doubleValue());
			operacao.setDataOperacao(Calendar.getInstance().getTime());
			try{
				operacao = operacaoService.create(operacao);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", rb.getString("salvaCompra")));
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");  
				String id = format.format(new Date());
				Map<String, Object> map = montaMapa();
				
				JasperReport pathRxml = JasperCompileManager.compileReport("D:/repo/dac/dac-crm/dac-crm-web/relatorios/vendas.jrxml");
				JasperPrint printReport = JasperFillManager.fillReport(pathRxml, map, new JRBeanCollectionDataSource(lstProdutos));
				JasperExportManager.exportReportToPdfFile(printReport,"D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatorio"+id+".pdf");
				File file = new File("D:/repo/dac/dac-crm/dac-crm-services/relatorios/relatorio"+id+".pdf");
				 
				ResponseBuilder response = Response.ok((Object) file);
				response.header("Content-Disposition",
						"attachment; filename=new-android-book.pdf");
				return response.build();
			}catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", rb.getString("erroCompra")));
				return null;
			}
		}
		return null;
	}
	
	public void buscaCliente(){
		CPFFormatter formatter = new CPFFormatter();
		String cpf = formatter.unformat(cliente.getCpf());
		cliente = (pessoaService.getByCPF(cpf));
		if(cliente == null){
			cliente = (new PessoaFisicaSummary());
			cliente.setNome(rb.getString("naoEncontrado"));
		}
	}
	
	public Map<String, Object> montaMapa(){
		Map<String, Object> map = new HashMap<>();
		map.put("nome", operacao.getNotaFiscal().getPessoa().getNome());
		map.put("numero", operacao.getNotaFiscal().getId());
		map.put("doc", cliente.getCpf());
		map.put("endereco", cliente.getEndereco().getEndereco());
		map.put("cidade", cliente.getEndereco().getCidade());
		map.put("complemento", cliente.getEndereco().getComplemento());
		map.put("cep", cliente.getEndereco().getCep());
		map.put("uf", cliente.getEndereco().getEstado());
		map.put("vlrTotal", operacao.getValorTotal());
		return map;
	}
	
	public void buscaProduto(){
		try{
			produto = produtoService.getOne(produto.getId());
		}catch(Exception e){
			e.printStackTrace();
			produto = new ProdutoSummary();
			produto.setDescricao(rb.getString("naoEncontrado"));
		}
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


