package org.ufpr.dac.wrapper;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.domain.Page;
import org.ufpr.dac.model.UsuarioSummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
public class UsuarioWrapper extends Wrapper<UsuarioSummary> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioWrapper(Page<? extends Object> page) {
		super(page);
	}
	
	public UsuarioWrapper(){
		super();
	}

}
