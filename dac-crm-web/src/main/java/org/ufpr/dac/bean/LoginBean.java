package org.ufpr.dac.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;
import org.ufpr.dac.model.UsuarioSummary;

@SessionScoped
@ManagedBean(name="loginBean")
@Component
public class LoginBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioSummary user = new UsuarioSummary();
	
	
	public UsuarioSummary getUser() {
		return user;
	}

	public void setUser(UsuarioSummary user) {
		this.user = user;
	}
}
