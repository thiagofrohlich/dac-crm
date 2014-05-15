package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	@RequestMapping(value="/compras/{dateini}/fim/{datafim}/doc/{cnpj}/produto/{produtoId}", method=RequestMethod.GET)
	public OperacaoWrapper getCompras(@PathVariable("dateini") String dateini,@PathVariable("datafim") String datafim, @PathVariable("cnpj") String cnpj,
			@PathVariable("produtoId") Long produtoId) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException, ParseException {
		List<Operacao> result = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");  
		Date ini = new Date(format.parse(dateini).getTime()); 
		Date fim = new Date(format.parse(datafim).getTime());
		if(cnpj.equals("VAZIO") && produtoId == 0) result = operacaoRepository.buscaCompraRelatorio(ini, fim);
		else if(cnpj.equals("VAZIO") && produtoId != 0)result = operacaoRepository.buscaCompraRelatorio(ini, fim, produtoId);
		else if(!cnpj.equals("VAZIO") && produtoId == 0)result = operacaoRepository.buscaCompraRelatorio(ini, fim, cnpj);
		else if(!cnpj.equals("VAZIO") && produtoId != 0)result = operacaoRepository.buscaCompraRelatorio(ini, fim, cnpj, produtoId);
		OperacaoWrapper wrapper = new OperacaoWrapper();
		wrapper.setList(new ArrayList<OperacaoSummary>(PageSize.DEFAULT));
		for(Operacao operacao : result) {
			OperacaoSummary p = instantiateOperacaoSummary();
			operacaoTransformer.transform(operacao, p);
			wrapper.getList().add(p);
		}
		
		return wrapper;
	}
	
	@ResponseBody
	@RequestMapping(value="/vendas/{dateini}{datafim}{cpf}{produtoId}", method=RequestMethod.GET)
	public OperacaoWrapper getVendas(@PathVariable Date dateini,@PathVariable Date datafim, @PathVariable String cpf, @PathVariable Long produtoId) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		List<Operacao> result = new ArrayList<>();
		if(cpf.equals("") && produtoId == 0) result = operacaoRepository.buscaCompraRelatorio(dateini, datafim);
		else if(cpf.equals("") && produtoId != 0)result = operacaoRepository.buscaCompraRelatorio(dateini, datafim, produtoId);
		else if(!cpf.equals("") && produtoId == 0)result = operacaoRepository.buscaCompraRelatorio(dateini, datafim, cpf);
		else if(!cpf.equals("") && produtoId != 0)result = operacaoRepository.buscaCompraRelatorio(dateini, datafim, cpf, produtoId);
		OperacaoWrapper wrapper = new OperacaoWrapper();
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
//		operacaoTransformer.transform(op, summary);
		return summary;
	}

	private OperacaoSummary instantiateOperacaoSummary() {
		return new OperacaoSummary();
	}

}
