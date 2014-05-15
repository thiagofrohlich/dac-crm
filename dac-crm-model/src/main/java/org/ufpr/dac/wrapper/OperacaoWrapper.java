package org.ufpr.dac.wrapper;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.OperacaoSummary;

public class OperacaoWrapper extends Wrapper<OperacaoSummary> {

	public OperacaoWrapper(Page<? extends Object> page) {
		super(page);
	}

	public OperacaoWrapper() {
		super();
	}

}
