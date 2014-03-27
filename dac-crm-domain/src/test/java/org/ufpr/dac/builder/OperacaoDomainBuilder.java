package org.ufpr.dac.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.ufpr.dac.domain.NotaFiscal;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.repository.OperacaoRepository;

@Component
public class OperacaoDomainBuilder {

	private final OperacaoRepository operacaoRepository;
	
	@Autowired
	public OperacaoDomainBuilder(OperacaoRepository operacaoRepository) {
		this.operacaoRepository = operacaoRepository;
	}
	
	private Long id = 1l;
	private double valorTotal = 99.99;
	private NotaFiscal notaFiscal;
	private boolean persisted = false;

	public Operacao asVenda() {
		Operacao venda = new Operacao();
		venda.setTipoOperacao("VENDA");
		setDefaultFields(venda);
		return persist(venda);
	}
	
	public Operacao asCompra() {
		Operacao compra = new Operacao();
		compra.setTipoOperacao("COMPRA");
		setDefaultFields(compra);
		return persist(compra);
	}
	
	private Operacao setDefaultFields(Operacao op) {
		op.setId(id);
		op.setNotaFiscal(notaFiscal != null ? notaFiscal : makeDefaultNotaFiscal(op));
		op.setValorTotal(valorTotal);
		return op;
	}

	private Operacao persist(Operacao op) {
		if(persisted) {
			return operacaoRepository.saveAndFlush(op);
		}
		return op;
	}

	private NotaFiscal makeDefaultNotaFiscal(Operacao op) {
		NotaFiscal nf = new NotaFiscal();
		nf.setId(id);
		nf.setObservacao("TESTE");
		nf.setOperacao(op);
		return nf;
	}

	public OperacaoDomainBuilder withId(Long id) {
		this.id = id;
		return this;
	}

	public OperacaoDomainBuilder withValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
		return this;
	}

	public OperacaoDomainBuilder withNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
		return this;
	}

	public OperacaoDomainBuilder persisted() {
		this.persisted = true;
		return this;
	}
}
