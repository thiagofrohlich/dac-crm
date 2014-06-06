package org.ufpr.dac.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.ufpr.dac.model.EnderecoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.service.PessoaServiceHandler;

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.caelum.stella.bean.validation.CPF;
import br.com.caelum.stella.format.CNPJFormatter;
import br.com.caelum.stella.format.CPFFormatter;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class pessoaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PessoaServiceHandler pessoaService = new PessoaServiceHandler();
	private PessoaFisicaSummary pessoaFisica = new PessoaFisicaSummary();
	private PessoaJuridicaSummary pessoaJuridica = new PessoaJuridicaSummary();
	private PessoaFisicaSummary pessoaFisicaSelecionada = new PessoaFisicaSummary();
	private PessoaJuridicaSummary pessoaJuridicaSelecionada = new PessoaJuridicaSummary();
	private Integer tipoPessoa = 1;
	private Integer tipoPessoaPesquisa = 1;
	private List<PessoaFisicaSummary> lstPessoaFisica = new ArrayList<>();
	private List<PessoaJuridicaSummary> lstPessoaJuridica = new ArrayList<>();
	@CPF(message="CPF inválido")
	private String cpf;
	@CNPJ(message="CNPJ inválido")
	private String cnpj;
	private ResourceBundle rb = ResourceBundle.getBundle("messages");
	private String nome;
	boolean apaga = false;
	
	public void salva(){
		if(tipoPessoa == 1){
			if(validaPessoaFisica()){
				try{
					CPFFormatter cpfFormatter = new CPFFormatter();
					pessoaFisica.setCpf(cpfFormatter.unformat(cpf));
					pessoaService.create(pessoaFisica);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", rb.getString("salvaPessoa")));
					apaga = false;
					limpaTela();
				}catch(Exception e){
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroPessoa")));
				}
			}
		}else{
			if(validaPessoaJuridica()){
				try{
					CNPJFormatter cnpjFormatter = new CNPJFormatter();
					pessoaJuridica.setCnpj(cnpjFormatter.unformat(cnpj));
					pessoaService.create(pessoaJuridica);
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", rb.getString("salvaPessoa")));
					apaga = false;
					limpaTela();
				}catch(Exception e){
					e.printStackTrace();
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroPessoa")));
				}
			}
		}
	}
	
	
	public void limpaTela() {
		pessoaFisica = new PessoaFisicaSummary();
		cpf = "";
		cnpj = "";
		pessoaFisicaSelecionada = new PessoaFisicaSummary();
		pessoaJuridica = new PessoaJuridicaSummary();
		pessoaJuridicaSelecionada = new PessoaJuridicaSummary();
		nome = "";
		
	}


	public boolean validaPessoaFisica(){
		boolean ret = true;
		if(pessoaFisica.getNome() == null || pessoaFisica.getNome().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erronome")));
			ret = false;
		}
		if(pessoaFisica.getEmail() == null || pessoaFisica.getEmail().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroemail")));
			ret = false;
		}
		ret = verificaEndereco(pessoaFisica.getEndereco());
		if(pessoaFisica.getTelefone() == null || pessoaFisica.getTelefone().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errotelefone")));
			ret = false;
		}
		return ret;
	}
	
	public boolean validaPessoaJuridica(){
		boolean ret = true;
		if(pessoaJuridica.getNome() == null || pessoaJuridica.getNome().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erronome")));
			ret = false;
		}
		if(pessoaJuridica.getEmail() == null || pessoaJuridica.getEmail().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroemail")));
			ret = false;
		}
		ret = verificaEndereco(pessoaJuridica.getEndereco());
		if(pessoaJuridica.getTelefone() == null || pessoaJuridica.getTelefone().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errotelefone")));
			ret = false;
		}
		return ret;
	}
	
	public boolean verificaEndereco(EnderecoSummary endereco){
		boolean ret = true;
		if(endereco.getCep() == null || endereco.getCep().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errocep")));
			ret = false;
		}
		if(endereco.getCidade() == null || endereco.getCidade().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("errocidade")));
			ret = false;
		}
		if(endereco.getEstado() == null || endereco.getEstado().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroestado")));
			ret = false;
		}
		
		if(endereco.getEndereco() == null || endereco.getEndereco().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erroendereco")));
			ret = false;
		}
		if(endereco.getNumero() == null || endereco.getNumero().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erronumero")));
			ret = false;
		}
		if(endereco.getPais() == null || endereco.getPais().equals("")){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO", rb.getString("erropais")));
			ret = false;
		}
		return ret;
	}
	
	
	public void apagar(){
		if(tipoPessoa == 1){
			pessoaService.delete(pessoaFisica.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", rb.getString("deletaProd")));
		}else{
			pessoaService.delete(pessoaJuridica.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", rb.getString("deletaProd")));
		}
		
	}
	
	public void pesquisaPessoa(){
		if(tipoPessoaPesquisa == 1){
			lstPessoaFisica = pessoaService.getByNome(nome).getLstPessoaFisica();
		}else{
			lstPessoaJuridica =  pessoaService.getByNome(nome).getLstPessoaJuridica();
		}
	}
	
	public void pesquisaPessoaFisica(){
		lstPessoaFisica = pessoaService.getByNome(nome).getLstPessoaFisica();
	}
	public void pesquisaPessoaJuridica(){
		lstPessoaJuridica =  pessoaService.getByNome(nome).getLstPessoaJuridica();
	}
	
	public void selecionaPessoa(){
		pessoaFisica = pessoaFisicaSelecionada;
		pessoaJuridica = pessoaJuridicaSelecionada;
		apaga = true;
	}
	
	
	public Integer getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}


	public PessoaFisicaSummary getPessoaFisica() {
		return pessoaFisica;
	}




	public void setPessoaFisica(PessoaFisicaSummary pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}




	public PessoaJuridicaSummary getPessoaJuridica() {
		return pessoaJuridica;
	}




	public void setPessoaJuridica(PessoaJuridicaSummary pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}




	public List<PessoaFisicaSummary> getLstPessoaFisica() {
		return lstPessoaFisica;
	}




	public void setLstPessoaFisica(List<PessoaFisicaSummary> lstPessoaFisica) {
		this.lstPessoaFisica = lstPessoaFisica;
	}




	public List<PessoaJuridicaSummary> getLstPessoaJuridica() {
		return lstPessoaJuridica;
	}




	public void setLstPessoaJuridica(List<PessoaJuridicaSummary> lstPessoaJuridica) {
		this.lstPessoaJuridica = lstPessoaJuridica;
	}




	public Integer getTipoPessoaPesquisa() {
		return tipoPessoaPesquisa;
	}




	public void setTipoPessoaPesquisa(Integer tipoPessoaPesquisa) {
		this.tipoPessoaPesquisa = tipoPessoaPesquisa;
	}


	public PessoaFisicaSummary getPessoaFisicaSelecionada() {
		return pessoaFisicaSelecionada;
	}


	public void setPessoaFisicaSelecionada(
			PessoaFisicaSummary pessoaFisicaSelecionada) {
		this.pessoaFisicaSelecionada = pessoaFisicaSelecionada;
	}


	public PessoaJuridicaSummary getPessoaJuridicaSelecionada() {
		return pessoaJuridicaSelecionada;
	}


	public void setPessoaJuridicaSelecionada(
			PessoaJuridicaSummary pessoaJuridicaSelecionada) {
		this.pessoaJuridicaSelecionada = pessoaJuridicaSelecionada;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	
	
	
}
