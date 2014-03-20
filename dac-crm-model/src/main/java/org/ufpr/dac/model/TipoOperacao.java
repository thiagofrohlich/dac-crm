package org.ufpr.dac.model;

import java.io.Serializable;

public enum TipoOperacao implements Serializable {
	
	COMPRA("COMPRA"),
	VENDA("VENDA");
	
	private String key;
	
	TipoOperacao(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
}
