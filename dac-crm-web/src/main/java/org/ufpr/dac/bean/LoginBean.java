package org.ufpr.dac.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.ufpr.dac.model.UsuarioSummary;
import org.ufpr.dac.service.UsuarioServiceHandler;

@SessionScoped
@ManagedBean(name="loginBean")
@Component
public class LoginBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioSummary user = new UsuarioSummary();
	private UsuarioServiceHandler usuarioService = new UsuarioServiceHandler();
	private boolean cadastro = false;
	private boolean compras = false;
	private boolean vendas = false;
	private boolean relatorio = false;
	
	public void recuperaUuario(){
		String u = (String)SecurityContextHolder.getContext().getAuthentication().getName();
		user = usuarioService.getByLogin(u);
		if(user.getAcessos().contains("COMPRAS")){
			compras = true;
		}
		if(user.getAcessos().contains("VENDAS")){
			vendas = true;
		}
		if(user.getAcessos().contains("CADASTRO")){
			cadastro = true;
		}
		if(user.getAcessos().contains("GERENTE_OPERACAO")){
			relatorio = true;
		}
	}
	
	public UsuarioSummary getUser() {
		return user;
	}

	public void setUser(UsuarioSummary user) {
		this.user = user;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public void setCadastro(boolean cadastro) {
		this.cadastro = cadastro;
	}

	public boolean isCompras() {
		return compras;
	}

	public void setCompras(boolean compras) {
		this.compras = compras;
	}

	public boolean isVendas() {
		return vendas;
	}

	public void setVendas(boolean vendas) {
		this.vendas = vendas;
	}

	public boolean isRelatorio() {
		return relatorio;
	}

	public void setRelatorio(boolean relatorio) {
		this.relatorio = relatorio;
	}
}
