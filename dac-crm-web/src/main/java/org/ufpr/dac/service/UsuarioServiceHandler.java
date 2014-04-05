package org.ufpr.dac.service;

import org.ufpr.dac.model.UsuarioSummary;
import org.ufpr.dac.wrapper.UsuarioWrapper;

public class UsuarioServiceHandler extends AbstractServiceHandler<UsuarioSummary, UsuarioWrapper, Long> {

	@Override
	public String getPath() {
		return super.getPath() + "usuario/";
	}

}
