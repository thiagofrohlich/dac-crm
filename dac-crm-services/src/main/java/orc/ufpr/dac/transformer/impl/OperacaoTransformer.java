package orc.ufpr.dac.transformer.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.domain.ProdutoNf;
import org.ufpr.dac.domain.ProdutoNfPK;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.ProdutoNfSummary;
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
			opDomain.getNotaFiscal().setProdutosNf(new ArrayList<ProdutoNf>());
			for(ProdutoNfSummary pnf : op.getNotaFiscal().getProdutosNf()){
				ProdutoNf nf = new ProdutoNf();
				nf.setId(new ProdutoNfPK());
				nf.getId().setProdutoId(pnf.getProdutoId());
				nf.getId().setNotaFiscal(opDomain.getNotaFiscal());
				nf.setQuantidade(pnf.getQuantidade());
				opDomain.getNotaFiscal().getProdutosNf().add(nf);
			}
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
