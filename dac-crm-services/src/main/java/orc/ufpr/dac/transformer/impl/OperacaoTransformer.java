package orc.ufpr.dac.transformer.impl;

import java.lang.reflect.InvocationTargetException;

import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.TipoOperacao;

@Component
public class OperacaoTransformer extends AbstractTransformer {

	@Override
	public void transform(Object objectFrom, Object objectTo)
			throws IllegalArgumentException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		super.transform(objectFrom, objectTo);
		
		if(isSummary(objectFrom)) {
			OperacaoSummary op = (OperacaoSummary) objectFrom;
			Operacao opDomain = (Operacao) objectTo;
			opDomain.setTipoOperacao(op.getTipoOperacao().getKey());
		}
		
		if(isDomain(objectFrom)) {
			Operacao opDomain = (Operacao) objectFrom;
			OperacaoSummary op = (OperacaoSummary) objectTo;
			op.setTipoOperacao(TipoOperacao.valueOf(opDomain.getTipoOperacao()));
		}
		
	}

	private boolean isDomain(Object objectFrom) {
		return objectFrom instanceof Operacao;
	}

	private boolean isSummary(Object objectFrom) {
		return objectFrom instanceof OperacaoSummary;
	}
	
}
