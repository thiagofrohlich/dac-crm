package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import orc.ufpr.dac.transformer.impl.ProdutoTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method=RequestMethod.GET)
	public HttpEntity<ProdutoWrapper> getAll(Pageable page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Page<Produto> result = produtoRepository.findAll(page);
		ProdutoWrapper wrapper = new ProdutoWrapper(result);
		wrapper.setList(new ArrayList<ProdutoSummary>(page.getPageSize()));
		
		for(Produto produto : result) {
			ProdutoSummary p = instantiateProdutoSummary(produto);
			produtoTransformer.transform(produto, p);
			wrapper.getList().add(p);
		}
		
		return new HttpEntity<ProdutoWrapper>(wrapper);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public HttpEntity<ProdutoSummary> getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Produto result = produtoRepository.findOne(id);
		ProdutoSummary summary = instantiateProdutoSummary(result);
		produtoTransformer.transform(result, summary);
		
		return new HttpEntity<ProdutoSummary>(summary);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<ProdutoSummary> create(final ProdutoSummary produto) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<ProdutoSummary>(saveOrUpdate(produto));
	}

	@RequestMapping(method=RequestMethod.PUT)
	public HttpEntity<ProdutoSummary> update(ProdutoSummary produto) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<ProdutoSummary>(saveOrUpdate(produto));
	}
	
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
