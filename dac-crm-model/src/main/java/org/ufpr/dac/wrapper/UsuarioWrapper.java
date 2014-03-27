package org.ufpr.dac.wrapper;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.UsuarioSummary;

public class UsuarioWrapper extends Wrapper<UsuarioSummary> {

	public UsuarioWrapper(Page<? extends Object> page) {
		super(page);
	}

}
