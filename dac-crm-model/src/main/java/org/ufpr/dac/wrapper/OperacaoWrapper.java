package org.ufpr.dac.wrapper;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.OperacaoSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class OperacaoWrapper extends Wrapper<OperacaoSummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperacaoWrapper(Page<? extends Object> page) {
		super(page);
	}

	public OperacaoWrapper() {
		super();
	}

	public OperacaoWrapper(List<OperacaoSummary> lista){
		super(lista);
	}
	
}
