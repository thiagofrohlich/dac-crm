package org.ufpr.dac.service;

import org.ufpr.dac.model.UsuarioSummary;
import org.ufpr.dac.wrapper.UsuarioWrapper;

public class UsuarioServiceHandler extends AbstractServiceHandler<UsuarioSummary, UsuarioWrapper, Long> {

	@Override
	public String getPath() {
		return super.getPath() + "usuario/";
	}
	
	@Override
	protected Class<?> getSummary() {
		return UsuarioSummary.class;
	}
	
	@Override
	protected Class<?> getWrapper() {
		return UsuarioWrapper.class;
	}
	
	public UsuarioSummary getByLogin(String login) {
		return (UsuarioSummary) getRestTemplate().getForObject(getPath()+"login/{login}", UsuarioSummary.class, login);
	}

}
