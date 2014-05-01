package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import orc.ufpr.dac.PageSize;
import orc.ufpr.dac.transformer.impl.UsuarioTransformer;

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
import org.ufpr.dac.domain.Usuario;
import org.ufpr.dac.model.UsuarioSummary;
import org.ufpr.dac.repository.UsuarioRepository;
import org.ufpr.dac.wrapper.UsuarioWrapper;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;
	private final UsuarioTransformer usuarioTransformer;

	@Autowired
	public UsuarioController(UsuarioTransformer usuarioTransformer, UsuarioRepository usuarioRepository) {
		this.usuarioTransformer = usuarioTransformer;
		this.usuarioRepository = usuarioRepository;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public UsuarioWrapper getAll(@PathVariable Integer page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Pageable pageRequest = new PageRequest(page, PageSize.DEFAULT);
		Page<Usuario> result = usuarioRepository.findAll(pageRequest);
		UsuarioWrapper wrapper = new UsuarioWrapper(result);
		wrapper.setList(new ArrayList<UsuarioSummary>(PageSize.DEFAULT));
		
		for(Usuario usuario : result) {
			UsuarioSummary p = instantiateUsuarioSummary(usuario);
			usuarioTransformer.transform(usuario, p);
			wrapper.getList().add(p);
		}
		
		return wrapper;
	}

	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public UsuarioSummary getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Usuario result = usuarioRepository.findOne(id);
		UsuarioSummary summary = instantiateUsuarioSummary(result);
		usuarioTransformer.transform(result, summary);
		
		return summary;
	}
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public UsuarioSummary create(@RequestBody UsuarioSummary usuario) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(usuario);
	}

	@ResponseBody
	@RequestMapping(method=RequestMethod.PUT)
	public UsuarioSummary update(@RequestBody UsuarioSummary usuario) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return saveOrUpdate(usuario);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		usuarioRepository.delete(id);
		return usuarioRepository.findOne(id) == null;
	}
	
	@ResponseBody
	@RequestMapping(value="/login/{login}", method=RequestMethod.GET)
	public UsuarioSummary getByLogin(@PathVariable String login) throws IllegalAccessException, InstantiationException, InvocationTargetException {
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		return transformUsuarioIfProvided(usuario);
	}

	private UsuarioSummary transformUsuarioIfProvided(Usuario usuario) throws IllegalAccessException,
			InstantiationException, InvocationTargetException {
		UsuarioSummary summary = new UsuarioSummary();
		if(usuario != null) {
			usuarioTransformer.transform(usuario, summary);
		} else {
			summary = null;
		}
		return summary;
	}
	
	private UsuarioSummary saveOrUpdate(final UsuarioSummary usuario)
			throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
		Usuario p = new Usuario();
		usuarioTransformer.transform(usuario, p);
		
		p = usuarioRepository.save(p);

		UsuarioSummary summary = instantiateUsuarioSummary(p);
		usuarioTransformer.transform(p, summary);
		return summary;
	}

	private UsuarioSummary instantiateUsuarioSummary(Usuario usuario) {
		return new UsuarioSummary();
	}
	
}
