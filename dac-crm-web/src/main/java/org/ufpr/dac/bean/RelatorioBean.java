package org.ufpr.dac.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.service.OperacaoServiceHandler;
import org.ufpr.dac.service.PessoaServiceHandler;
import org.ufpr.dac.service.ProdutoServiceHandler;

import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;
@ViewScoped
@ManagedBean(name="relatorioBean")
public class RelatorioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PessoaServiceHandler pessoaService = new PessoaServiceHandler();
	private ProdutoServiceHandler produtoService = new ProdutoServiceHandler();
	private OperacaoServiceHandler operacaoService = new OperacaoServiceHandler();
	private ResourceBundle rb = ResourceBundle.getBundle("messages");
	private Integer tipoPesquisa = 1;
	private Date dataini;
	private Date datafim;
	private PessoaJuridicaSummary pessoaJuridica = new PessoaJuridicaSummary();
	private PessoaFisicaSummary pessoaFisica = new PessoaFisicaSummary();
	private ProdutoSummary produto = new ProdutoSummary();
	private List<OperacaoSummary> lstOperacao = new ArrayList<>();
	private String doc;
	
	
	
		
	
	public void buscaCliente(){
		CPFFormatter formatter = new CPFFormatter();
		String cpf = formatter.unformat(pessoaFisica.getCpf());
		pessoaFisica = (pessoaService.getByCPF(cpf));
		if(pessoaFisica == null){
			pessoaFisica = (new PessoaFisicaSummary());
			pessoaFisica.setNome(rb.getString("naoEncontrado"));
		}
	}
	
	
	public void buscaFornecedor(){
		CNPJFormatter formatter = new CNPJFormatter();
		String cnpj = formatter.unformat(pessoaJuridica.getCnpj());
		pessoaJuridica = (pessoaService.getByCnpj(cnpj));
		if(pessoaJuridica == null){
			pessoaJuridica = (new PessoaJuridicaSummary());
			pessoaJuridica.setNome(rb.getString("naoEncontrado"));
		}
	}
	
	public void pesquisar(){
		
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		String ini = "";
		String fim = "";
		ini = f.format(dataini);
		fim = f.format(datafim);
		byte[] bt = null;
	
		if(tipoPesquisa == 1) bt = operacaoService.getCompras(ini, fim, pessoaJuridica.getCnpj(), produto.getId());
		else bt = operacaoService.getVendas(dataini, datafim, pessoaFisica.getCpf(), produto.getId());
		try{
			FacesContext context = FacesContext.getCurrentInstance();  
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();  
			response.reset();  
			response.setContentType("application/pdf");  
			response.setHeader("Content-Disposition", "attachment; filename=Relatorio.pdf");  
			response.setHeader("Cache-Control", "no-cache");  
			response.getOutputStream().write(bt);  
			response.getOutputStream().flush();  
			response.getOutputStream().close();  
			context.responseComplete();  
			dataini = null;
			datafim = null;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void confirmaCampos(){
		if(tipoPesquisa == 1){
			if(pessoaJuridica == null || pessoaJuridica.getCnpj() == null){
				pessoaJuridica = new PessoaJuridicaSummary();
				pessoaJuridica.setCnpj("VAZIO");
			}
		}else{
			if(pessoaFisica == null || pessoaFisica.getCpf() == null){
				pessoaFisica = new PessoaFisicaSummary();
				pessoaFisica.setCpf("VAZIO");
			}
		}
	
		if(produto == null || produto.getId() == null){
			produto = new ProdutoSummary();
			produto.setId((long) 0);
		}
		pesquisar();
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
	
	
	public Date getDataini() {
		return dataini;
	}
	public void setDataini(Date dataini) {
		this.dataini = dataini;
	}
	public Date getDatafim() {
		return datafim;
	}
	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}
	public ProdutoSummary getProduto() {
		return produto;
	}
	public void setProduto(ProdutoSummary produto) {
		this.produto = produto;
	}
	public List<OperacaoSummary> getLstOperacao() {
		return lstOperacao;
	}
	public void setLstOperacao(List<OperacaoSummary> lstOperacao) {
		this.lstOperacao = lstOperacao;
	}



	public String getDoc() {
		return doc;
	}



	public void setDoc(String doc) {
		this.doc = doc;
	}


	public PessoaJuridicaSummary getPessoaJuridica() {
		return pessoaJuridica;
	}


	public void setPessoaJuridica(PessoaJuridicaSummary pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}


	public PessoaFisicaSummary getPessoaFisica() {
		return pessoaFisica;
	}


	public void setPessoaFisica(PessoaFisicaSummary pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}
}
