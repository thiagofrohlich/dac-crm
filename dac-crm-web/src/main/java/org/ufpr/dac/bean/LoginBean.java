package org.ufpr.dac.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.ufpr.dac.model.UsuarioSummary;
@SessionScoped
@ManagedBean(name="loginBean")
public class LoginBean {
	
	
	private UsuarioSummary user = new UsuarioSummary();

	public UsuarioSummary getUser() {
		return user;
	}

	public void setUser(UsuarioSummary user) {
		this.user = user;
	}
}
