package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import orc.ufpr.dac.transformer.impl.PessoaTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.ufpr.dac.domain.Pessoa;
import org.ufpr.dac.model.PessoaFisicaSummary;
import org.ufpr.dac.model.PessoaJuridicaSummary;
import org.ufpr.dac.model.PessoaSummary;
import org.ufpr.dac.repository.PessoaRepository;
import org.ufpr.dac.wrapper.PessoaWrapper;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {
	
	private final PessoaRepository pessoaRepository;
	private final PessoaTransformer pessoaTransformer;

	@Autowired
	public PessoaController(PessoaTransformer pessoaTransformer, PessoaRepository pessoaRepository) {
		this.pessoaTransformer = pessoaTransformer;
		this.pessoaRepository = pessoaRepository;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public HttpEntity<PessoaWrapper> getAll(Pageable page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Page<Pessoa> result = pessoaRepository.findAll(page);
		PessoaWrapper wrapper = new PessoaWrapper(result);
		wrapper.setList(new ArrayList<PessoaSummary>(page.getPageSize()));
		
		for(Pessoa pessoa : result) {
			PessoaSummary p = instantiatePessoaSummary(pessoa);
			pessoaTransformer.transform(pessoa, p);
			wrapper.getList().add(p);
		}
		
		return new HttpEntity<PessoaWrapper>(wrapper);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public HttpEntity<PessoaSummary> getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pessoa result = pessoaRepository.findOne(id);
		PessoaSummary summary = instantiatePessoaSummary(result);
		pessoaTransformer.transform(result, summary);
		
		return new HttpEntity<PessoaSummary>(summary);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<PessoaSummary> create(final PessoaSummary pessoa) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<PessoaSummary>(saveOrUpdate(pessoa));
	}

	@RequestMapping(method=RequestMethod.PUT)
	public HttpEntity<PessoaSummary> update(PessoaSummary pessoa) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<PessoaSummary>(saveOrUpdate(pessoa));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		pessoaRepository.delete(id);
		return pessoaRepository.findOne(id) == null;
	}
	
	private PessoaSummary saveOrUpdate(final PessoaSummary pessoa)
			throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		Pessoa p = new Pessoa();
		pessoaTransformer.transform(pessoa, p);
		
		p = pessoaRepository.save(p);

		PessoaSummary summary = instantiatePessoaSummary(p);
		pessoaTransformer.transform(p, summary);
		return summary;
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