package org.ufpr.dac.wrapper;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.ProdutoSummary;

public class ProdutoWrapper extends Wrapper<ProdutoSummary> {

	public ProdutoWrapper(Page<? extends Object> page) {
		super(page);
	}

}
