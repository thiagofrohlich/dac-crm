package orc.ufpr.dac.rest;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import orc.ufpr.dac.transformer.impl.UsuarioTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(method=RequestMethod.GET)
	public HttpEntity<UsuarioWrapper> getAll(Pageable page) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Page<Usuario> result = usuarioRepository.findAll(page);
		UsuarioWrapper wrapper = new UsuarioWrapper(result);
		wrapper.setList(new ArrayList<UsuarioSummary>(page.getPageSize()));
		
		for(Usuario usuario : result) {
			UsuarioSummary p = instantiateUsuarioSummary(usuario);
			usuarioTransformer.transform(usuario, p);
			wrapper.getList().add(p);
		}
		
		return new HttpEntity<UsuarioWrapper>(wrapper);
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public HttpEntity<UsuarioSummary> getOne(@PathVariable Long id) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		Usuario result = usuarioRepository.findOne(id);
		UsuarioSummary summary = instantiateUsuarioSummary(result);
		usuarioTransformer.transform(result, summary);
		
		return new HttpEntity<UsuarioSummary>(summary);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public HttpEntity<UsuarioSummary> create(final UsuarioSummary usuario) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<UsuarioSummary>(saveOrUpdate(usuario));
	}

	@RequestMapping(method=RequestMethod.PUT)
	public HttpEntity<UsuarioSummary> update(UsuarioSummary usuario) throws IllegalArgumentException, IllegalAccessException, InstantiationException, InvocationTargetException {
		return new HttpEntity<UsuarioSummary>(saveOrUpdate(usuario));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public boolean delete(@PathVariable Long id) {
		usuarioRepository.delete(id);
		return usuarioRepository.findOne(id) == null;
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
