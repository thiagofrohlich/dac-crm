package org.ufpr.dac.service;

import java.util.Date;

import org.springframework.http.ResponseEntity;
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
	
	public Long createReturn(OperacaoSummary operacao){
		return getRestTemplate().postForObject(getPath()+"long/", operacao, Long.class);
	}
	
	public byte[] getCompras(String dateini, String datafim, String cnpj, Long produtoId) {
		System.out.println(getPath()+"compras/"+dateini+"/fim/"+datafim+"/doc/"+cnpj+"/produto/"+produtoId);
		return getRestTemplate().getForObject(getPath()+"compras/{dateini}/fim/{datafim}/doc/{cnpj}/produto/{produtoId}", byte[].class, dateini, datafim, cnpj, produtoId);
	}
	
	public byte[] getVendas(String dateini, String datafim, String cpf, Long produtoId) {
		System.out.println(getPath()+"vendas/"+dateini+"/fim/"+datafim+"/doc/"+cpf+"/produto/"+produtoId);
		return   getRestTemplate().getForObject(getPath()+"vendas/{dateini}/fim/{datafim}/doc/{cpf}/produto/{produtoId}", byte[].class, dateini, datafim, cpf, produtoId);
	}
	
	
	
}
