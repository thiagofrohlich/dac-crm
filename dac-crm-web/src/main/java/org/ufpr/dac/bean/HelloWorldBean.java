package org.ufpr.dac.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "helloWorldBean")
public class HelloWorldBean {
	
	public HelloWorldBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String getWelcomeMessage() {
		return "Hello Primefaces!!";
	}

}
