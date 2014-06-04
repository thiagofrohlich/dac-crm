package org.ufpr.dac.wrapper;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.ProdutoSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@XmlSeeAlso(ProdutoSummary.class)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProdutoWrapper extends Wrapper<ProdutoSummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProdutoWrapper(Page<? extends Object> page) {
		super(page);
	}

	public ProdutoWrapper(){
		super();
	}
}
