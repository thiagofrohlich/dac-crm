package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import orc.ufpr.dac.PageSize;
import orc.ufpr.dac.transformer.impl.OperacaoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@ResponseBody
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET)
	public OperacaoWrapper getAll(@PathVariable Integer page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pageable pageRequest = new PageRequest(page, PageSize.DEFAULT);
		Page<Operacao> result = operacaoRepository.findAll(pageRequest);
		OperacaoWrapper wrapper = new OperacaoWrapper(result);
		wrapper.setList(new ArrayList<OperacaoSummary>(PageSize.DEFAULT));
		
		for(Operacao operacao : result) {
			OperacaoSummary p = instantiateOperacaoSummary();
			operacaoTransformer.transform(operacao, p);
			wrapper.getList().add(p);
		}
		
		return wrapper;
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public OperacaoSummary getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Operacao result = operacaoRepository.findOne(id);
		OperacaoSummary summary = instantiateOperacaoSummary();
		operacaoTransformer.transform(result, summary);
		
		return summary;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public OperacaoSummary create(@RequestBody OperacaoSummary operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(operacao);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public OperacaoSummary update(@RequestBody OperacaoSummary operacao) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(operacao);
	}
	
	@ResponseBody
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
