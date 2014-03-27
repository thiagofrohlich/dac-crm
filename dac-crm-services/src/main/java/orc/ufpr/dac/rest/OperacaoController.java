package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import orc.ufpr.dac.transformer.impl.OperacaoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ufpr.dac.domain.Operacao;
import org.ufpr.dac.model.OperacaoSummary;
import org.ufpr.dac.repository.OperacaoRepository;
import org.ufpr.dac.wrapper.OperacaoWrapper;

@Controller
@RequestMapping("/operacao")
public class OperacaoController {
	
	private final OperacaoTransformer operacaoTransformer;
	private final OperacaoRepository operacaoRepository;

	@Autowired
	public OperacaoController(OperacaoTransformer operacaoTransformer,
			OperacaoRepository operacaoRepository) {
		this.operacaoTransformer = operacaoTransformer;
		this.operacaoRepository = operacaoRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public HttpEntity<OperacaoWrapper> getAll(Pageable page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Page<Operacao> result = operacaoRepository.findAll(page);
		OperacaoWrapper wrapper = new OperacaoWrapper(result);
		wrapper.setList(new ArrayList<OperacaoSummary>(page.getPageSize()));
		
		for(Operacao operacao : result) {
			OperacaoSummary p = instantiateOperacaoSummary();
			operacaoTransformer.transform(operacao, p);
			wrapper.getList().add(p);
		}
		
		return new HttpEntity<OperacaoWrapper>(wrapper);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public HttpEntity<OperacaoSummary> getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Operacao result = operacaoRepository.findOne(id);
		OperacaoSummary summary = instantiateOperacaoSummary();
		operacaoTransformer.transform(result, summary);
		
		return new HttpEntity<OperacaoSummary>(summary);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<OperacaoSummary> create(final OperacaoSummary Operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<OperacaoSummary>(saveOrUpdate(Operacao));
	}

	@RequestMapping(method=RequestMethod.PUT)
	public HttpEntity<OperacaoSummary> update(OperacaoSummary Operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<OperacaoSummary>(saveOrUpdate(Operacao));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		operacaoRepository.delete(id);
		return operacaoRepository.findOne(id) == null;
	}

	private OperacaoSummary saveOrUpdate(OperacaoSummary operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Operacao op = new Operacao();
		operacaoTransformer.transform(operacao, op);
		
		op = operacaoRepository.save(op);
		
		OperacaoSummary summary = instantiateOperacaoSummary();
		operacaoTransformer.transform(op, summary);
		return summary;
	}

	private OperacaoSummary instantiateOperacaoSummary() {
		return new OperacaoSummary();
	}

}
