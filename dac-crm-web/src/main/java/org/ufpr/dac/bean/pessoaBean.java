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

import br.com.caelum.stella.bean.validation.CNPJ;
import br.com.caelum.stella.bean.validation.CPF;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class pessoaBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public void salva(){
		if(tipoPessoa == 1){
			if(validaPessoaFisica()){
				
			}
		}else{
			if(validaPessoaJuridica()){
				
			}
		}
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


	
	
	
}
