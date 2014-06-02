package orc.ufpr.dac.transformer.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.domain.ProdutoNf;
import org.ufpr.dac.domain.ProdutoNfPK;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.model.ProdutoNfSummary;
import org.ufpr.dac.model.TipoOperacao;

@Component
public class OperacaoTransformer extends AbstractTransformer {
	
	private PessoaTransformer pessoaTransformer = new PessoaTransformer();
	
	@Override
	public void transform(Object objectFrom, Object objectTo)
			throws IllegalArgumentException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		super.transform(objectFrom, objectTo);
		
		if(isSummary(objectFrom)) {
			OperacaoSummary op = (OperacaoSummary) objectFrom;
			Operacao opDomain = (Operacao) objectTo;
			opDomain.setTipoOperacao(op.getTipoOperacao().getKey());
			opDomain.getNotaFiscal().setProdutosNf(null);
			
		}
		
		if(isDomain(objectFrom)) {
			Operacao opDomain = (Operacao) objectFrom;
			OperacaoSummary op = (OperacaoSummary) objectTo;
			op.setTipoOperacao(TipoOperacao.valueOf(opDomain.getTipoOperacao()));
			transformNotaFiscalPessoa(opDomain, op);
			op.getNotaFiscal().setProdutosNf(new ArrayList<ProdutoNfSummary>());
			for(ProdutoNf pnf : opDomain.getNotaFiscal().getProdutosNf()){
				ProdutoNfSummary pNfSummary = new ProdutoNfSummary();
				pNfSummary.setNfId(pnf.getId().getNotaFiscal());
				pNfSummary.setProdutoId(pnf.getId().getProdutoId());
				pNfSummary.setQuantidade(pnf.getQuantidade());
				op.getNotaFiscal().getProdutosNf().add(pNfSummary);
			}
			
		}
		
		
	}

	private void transformNotaFiscalPessoa(Operacao opDomain, OperacaoSummary op)
			throws IllegalAccessException, InstantiationException,
			InvocationTargetException {
		Pessoa pessoaDomain = opDomain.getNotaFiscal().getPessoa();
		PessoaSummary pessoaSummary = instantiatePessoaSummary(pessoaDomain);
		pessoaTransformer.transform(pessoaDomain, pessoaSummary);
		op.getNotaFiscal().setPessoa(pessoaSummary);
	}

	private boolean isDomain(Object objectFrom) {
		return objectFrom instanceof Operacao;
	}

	private boolean isSummary(Object objectFrom) {
		return objectFrom instanceof OperacaoSummary;
	}
	
	private PessoaSummary instantiatePessoaSummary(Pessoa pessoa) {
		PessoaSummary p = null;
		if(pessoa.getPessoaFisica() != null) {
			p = new PessoaFisicaSummary();
		} else if(pessoa.getPessoaJuridica() != null) {
			p = new PessoaJuridicaSummary();
		}
		return p;
	}

	
}
