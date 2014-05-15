package org.ufpr.dac.service;

import java.util.Date;

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
	
	public OperacaoWrapper getCompras(String dateini, String datafim, String cnpj, Long produtoId) {
		System.out.println(getPath()+"compras/"+dateini+"/fim/"+datafim+"/doc/"+cnpj+"/produto/"+produtoId+"");
		return   getRestTemplate().getForObject(getPath()+"compras/{dateini}/fim/{datafim}/doc/{cnpj}/produto/{produtoId}", OperacaoWrapper.class, dateini, datafim, cnpj, produtoId);
	}
	
	public OperacaoWrapper getVendas(Date dateini, Date datafim, String cpf, Long produtoId) {
		return   getRestTemplate().getForObject(getPath()+"vendas/{dateini}{datafim}{cpf}{produtoId}", OperacaoWrapper.class, dateini, datafim, cpf, produtoId);
	}
	
}
