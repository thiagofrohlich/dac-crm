package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import orc.ufpr.dac.PageSize;
import orc.ufpr.dac.transformer.impl.PessoaTransformer;

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
	
	@ResponseBody
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET)
	public PessoaWrapper getAll(@PathVariable Integer page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pageable pageRequest = new PageRequest(page, PageSize.DEFAULT);
		Page<Pessoa> result = pessoaRepository.findAll(pageRequest);
		PessoaWrapper wrapper = new PessoaWrapper(result);
		wrapper.setList(new ArrayList<PessoaSummary>(PageSize.DEFAULT));
		
		for(Pessoa pessoa : result) {
			PessoaSummary p = instantiatePessoaSummary(pessoa);
			pessoaTransformer.transform(pessoa, p);
			wrapper.getList().add(p);
		}
		
		return wrapper;
	}
	
	@ResponseBody
	@RequestMapping(value="/nome/{nome}", method=RequestMethod.GET)
	public PessoaWrapper getByNome(@PathVariable String nome) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		List<Pessoa> result = pessoaRepository.findByNome("%"+nome+"%");
		PessoaWrapper wrapper = new PessoaWrapper();
		wrapper.setList(new ArrayList<PessoaSummary>(PageSize.DEFAULT));
		
		for(Pessoa pessoa : result) {
			PessoaSummary p = instantiatePessoaSummary(pessoa);
			pessoaTransformer.transform(pessoa, p);
			if(p instanceof PessoaFisicaSummary){
				wrapper.getLstPessoaFisica().add((PessoaFisicaSummary) p);
			}else{
				wrapper.getLstPessoaJuridica().add((PessoaJuridicaSummary) p);
			}
		}
		
		return wrapper;
	}
	
	
	

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public PessoaSummary getOne(@PathVariable final Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pessoa result = pessoaRepository.findOne(id);
		PessoaSummary summary = instantiatePessoaSummary(result);
		pessoaTransformer.transform(result, summary);
		return summary;
	}
	
	@ResponseBody
	@RequestMapping(value="/cnpj/{cnpj}", method=RequestMethod.GET)
	public PessoaJuridicaSummary getByCNPJ(@PathVariable final String cnpj) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pessoa result = pessoaRepository.findByCNPJ(cnpj);
		if(result != null){
			PessoaSummary summary = instantiatePessoaSummary(result);
			pessoaTransformer.transform(result, summary);
			return (PessoaJuridicaSummary) summary;
		}else{
			return null;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/cpf/{cpf}", method=RequestMethod.GET)
	public PessoaFisicaSummary getByCPF(@PathVariable final String cpf) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pessoa result = pessoaRepository.findByCPF(cpf);
		if(result != null){
			PessoaSummary summary = instantiatePessoaSummary(result);
			pessoaTransformer.transform(result, summary);
			return (PessoaFisicaSummary) summary;
		}else{
			return null;
		}
		
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public PessoaSummary create(@RequestBody PessoaSummary pessoa) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(pessoa);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public PessoaSummary update(@RequestBody PessoaSummary pessoa) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(pessoa);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable final Long id) {
		pessoaRepository.delete(id);
		return pessoaRepository.findOne(id) == null;
	}
	
	private PessoaSummary saveOrUpdate(final PessoaSummary pessoa)
			throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		Pessoa p = new Pessoa();
		pessoaTransformer.transform(pessoa, p);
		
		p = pessoaRepository.save(p);
		if(p.getPessoaFisica() != null){
			p.getPessoaFisica().setPessoa(p);
		}else{
			p.getPessoaJuridica().setPessoa(p);
		}
		p.getEndereco().setPessoa(p);
		p=pessoaRepository.save(p);
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
