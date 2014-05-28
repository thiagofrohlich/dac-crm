package org.ufpr.dac.service;

import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.wrapper.OperacaoWrapper;

public class OperacaoServiceHandler extends AbstractServiceHandler<OperacaoSummary, OperacaoWrapper, Long> {

	@Override
	protected Class<?> getSummary() {
		return OperacaoSummary.class;
	}
	
	@Override
	protected Class<?> getWrapper() {
		return OperacaoWrapper.class;
	}
	
	@Override
	public String getPath() {
		return super.getPath() + "operacao/";
	}
	
	public byte[] getCompras(String dateini, String datafim, String cnpj, Long produtoId) {
		System.out.println(getPath()+"compras/"+dateini+"/fim/"+datafim+"/doc/"+cnpj+"/produto/"+produtoId);
		return getRestTemplate().getForObject(getPath()+"compras/{dateini}/fim/{datafim}/doc/{cnpj}/produto/{produtoId}", byte[].class, dateini, datafim, cnpj, produtoId);
	}
	
	public byte[] getVendas(Date dateini, Date datafim, String cpf, Long produtoId) {
		return   getRestTemplate().getForObject(getPath()+"vendas/{dateini}/fim/{datafim}/doc/{cpf}/produto/{produtoId}", byte[].class, dateini, datafim, cpf, produtoId);
	}
	
}
