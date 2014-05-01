package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import orc.ufpr.dac.PageSize;
import orc.ufpr.dac.transformer.impl.ProdutoTransformer;

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
import org.ufpr.dac.domain.Produto;
import org.ufpr.dac.model.ProdutoSummary;
import org.ufpr.dac.repository.ProdutoRepository;
import org.ufpr.dac.wrapper.ProdutoWrapper;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private final ProdutoRepository produtoRepository;
	private final ProdutoTransformer produtoTransformer;

	@Autowired
	public ProdutoController(ProdutoTransformer produtoTransformer, ProdutoRepository produtoRepository) {
		this.produtoTransformer = produtoTransformer;
		this.produtoRepository = produtoRepository;
	}
	
	@ResponseBody
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET)
	public ProdutoWrapper getAll(@PathVariable Integer page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pageable pageRequest = new PageRequest(page, PageSize.DEFAULT);
		Page<Produto> result = produtoRepository.findAll(pageRequest);
		ProdutoWrapper wrapper = new ProdutoWrapper(result);
		wrapper.setList(new ArrayList<ProdutoSummary>(PageSize.DEFAULT));
		
		for(Produto produto : result) {
			ProdutoSummary p = instantiateProdutoSummary(produto);
			produtoTransformer.transform(produto, p);
			wrapper.getList().add(p);
		}
		
		return wrapper;
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ProdutoSummary getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Produto result = produtoRepository.findOne(id);
		ProdutoSummary summary = instantiateProdutoSummary(result);
		produtoTransformer.transform(result, summary);
		
		return summary;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public ProdutoSummary create(@RequestBody ProdutoSummary produto) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(produto);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public ProdutoSummary update(@RequestBody ProdutoSummary produto) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(produto);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		produtoRepository.delete(id);
		return produtoRepository.findOne(id) == null;
	}
	
	private ProdutoSummary saveOrUpdate(final ProdutoSummary produto)
			throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		Produto p = new Produto();
		produtoTransformer.transform(produto, p);
		
		p = produtoRepository.save(p);

		ProdutoSummary summary = instantiateProdutoSummary(p);
		produtoTransformer.transform(p, summary);
		return summary;
	}

	private ProdutoSummary instantiateProdutoSummary(Produto produto) {
		return new ProdutoSummary();
	}

}
