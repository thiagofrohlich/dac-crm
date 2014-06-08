package org.ufpr.dac.bean;

import java.io.File;
import java.io.FileInputStream;
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
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.ProdutoNfSummary;
import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.model.TipoOperacao;
import org.ufpr.dac.service.OperacaoServiceHandler;
import org.ufpr.dac.service.PessoaServiceHandler;
import org.ufpr.dac.service.ProdutoServiceHandler;

import br.com.caelum.stella.format.CNPJFormatter;

@ViewScoped
@ManagedBean(name="comprasBean")
public class ComprasBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PessoaServiceHandler pessoaService = new PessoaServiceHandler();
	private ProdutoServiceHandler produtoService = new ProdutoServiceHandler();
	private OperacaoServiceHandler operacaoService = new OperacaoServiceHandler();
	private OperacaoSummary operacao = new OperacaoSummary();
	private PessoaJuridicaSummary fornecedor = new PessoaJuridicaSummary();
	private ProdutoSummary produto = new ProdutoSummary();
	private List<ProdutoSummary> lstProdutos = new ArrayList<>();
	private Integer tipoPesquisa = 1;
	private BigDecimal subTotal = new BigDecimal(0);
	private BigDecimal acrescimos = new BigDecimal(0);
	private BigDecimal descontos = new BigDecimal(0);
	private Integer pagto;
	private PessoaJuridicaSummary fornecedorSelecionado = new PessoaJuridicaSummary();
	private ResourceBundle rb = ResourceBundle.getBundle("messages");
	private ResourceBundle app = ResourceBundle.getBundle("app");
	private ProdutoSummary prodSelecionado = new ProdutoSummary();


	public void lancar(){
		ProdutoNfSummary produtoNf = new ProdutoNfSummary();
		produtoNf.setProdutoId(produto.getId());
		produtoNf.setQuantidade(produto.getQtd());
		if(operacao.getNotaFiscal().getProdutosNf().contains(produtoNf)){
			operacao.getNotaFiscal().getProdutosNf().get(operacao.getNotaFiscal().getProdutosNf().indexOf(produtoNf)).somaQtd(produtoNf.getQuantidade());
		}else{
			operacao.getNotaFiscal().getProdutosNf().add(produtoNf);
		}
		lstProdutos.add(produto);
		BigDecimal valor = new BigDecimal(produto.getValorCompra()*produto.getQtd());
		valor = valor.setScale(2, BigDecimal.ROUND_HALF_UP);
		produto = new ProdutoSummary();
		subTotal = subTotal.add(valor);
	}
	
	public void apagarProd(ProdutoSummary prod){
		lstProdutos.remove(prod);
		ProdutoNfSummary produtoNf = new ProdutoNfSummary();
		produtoNf.setProdutoId(prod.getId());
		produtoNf.setQuantidade(prod.getQtd());
		operacao.getNotaFiscal().getProdutosNf().remove(produtoNf);
		BigDecimal valor = new BigDecimal(prod.getValorCompra()*prod.getQtd());
		valor = valor.setScale(2, BigDecimal.ROUND_HALF_UP);
		subTotal =  subTotal.subtract(valor);
	}
	
	public void somaAcrescimo(){
		operacao.setValorTotal(subTotal.add(acrescimos).doubleValue());
	}
	
	public void subtraiDesconto(){
		operacao.setValorTotal(subTotal.subtract(descontos).doubleValue());
	}
	
	public boolean validaCompra(){
		boolean ret = true;
		if(fornecedor.getRootId() == null || fornecedor.getRootId() == 0){
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
	
	public void salva(){
		if(validaCompra()){
			operacao.setTipoOperacao(TipoOperacao.COMPRA);
			operacao.getNotaFiscal().setPessoa(fornecedor);
			operacao.setValorTotal(subTotal.doubleValue());
			operacao.setDataOperacao(Calendar.getInstance().getTime());
			try{
				operacao.setId(operacaoService.createReturn(operacao));
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", rb.getString("salvaCompra")));
				Map<String, Object> map = montaMapa();
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");  
				String id = format.format(new Date());
				
				JasperReport pathRxml = JasperCompileManager.compileReport(app.getString("notaCompra")+"compra.jrxml");
				JasperPrint printReport = JasperFillManager.fillReport(pathRxml, map, new JRBeanCollectionDataSource(lstProdutos));
				JasperExportManager.exportReportToPdfFile(printReport,app.getString("notaVenda")+id+".pdf");
				File file = new File(app.getString("notaVenda")+id+".pdf");
				FacesContext context = FacesContext.getCurrentInstance();  
				HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();  
				response.reset();  
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment; filename=comprovante_de_compra.pdf");  
				response.setHeader("Cache-Control", "no-cache"); 
				FileInputStream fis = new FileInputStream(file);  
		        byte[] data = new byte[fis.available()];  
		        fis.read(data);  
		        fis.close();
				response.getOutputStream().write(data);  
				response.getOutputStream().flush();  
				response.getOutputStream().close();  
				context.responseComplete();
				limpaTela();
			}catch(Exception e){
				e.printStackTrace();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroSalvaCompra")));
			}
		}
	}
	
	public void limpaTela() {
		operacao = new OperacaoSummary();
		produto = new ProdutoSummary();
		prodSelecionado = new ProdutoSummary();
		fornecedor = new PessoaJuridicaSummary();
		fornecedorSelecionado = new PessoaJuridicaSummary();
		lstProdutos.clear();
		subTotal = new BigDecimal(0);
	}

	public void buscaFornecedor(){
		CNPJFormatter formatter = new CNPJFormatter();
		String cnpj = formatter.unformat(fornecedor.getCnpj());
		fornecedor = (pessoaService.getByCnpj(cnpj));
		if(fornecedor == null){
			fornecedor = (new PessoaJuridicaSummary());
			fornecedor.setNome(rb.getString("naoEncontrado"));
		}
	}
	
	public Map<String, Object> montaMapa(){
		Map<String, Object> map = new HashMap<>();
		map.put("nome", operacao.getNotaFiscal().getPessoa().getNome());
		map.put("numero", operacao.getNotaFiscal().getId());
		map.put("doc", fornecedor.getCnpj());
		map.put("endereco", fornecedor.getEndereco().getEndereco()+" "+fornecedor.getEndereco().getNumero()+" "+ fornecedor.getEndereco().getComplemento()!=null?fornecedor.getEndereco().getComplemento():"");
		map.put("cidade", fornecedor.getEndereco().getCidade());
		map.put("cep", fornecedor.getEndereco().getCep());
		map.put("uf", fornecedor.getEndereco().getEstado());
		map.put("vlrTotal", operacao.getValorTotal());
		return map;
	}
	
	public void SelecionaFornecedor(){
		fornecedor = fornecedorSelecionado;
	}
	
	public void selecionaProduto(){
		produto = prodSelecionado;
	}
	
	public void buscaProduto(){
		produto = produtoService.getOne(produto.getId());
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

	public PessoaJuridicaSummary getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(PessoaJuridicaSummary fornecedor) {
		this.fornecedor = fornecedor;
	}

	public PessoaJuridicaSummary getFornecedorSelecionado() {
		return fornecedorSelecionado;
	}

	public void setFornecedorSelecionado(PessoaJuridicaSummary fornecedorSelecionado) {
		this.fornecedorSelecionado = fornecedorSelecionado;
	}

	public ProdutoSummary getProdSelecionado() {
		return prodSelecionado;
	}

	public void setProdSelecionado(ProdutoSummary prodSelecionado) {
		this.prodSelecionado = prodSelecionado;
	}
	
}
