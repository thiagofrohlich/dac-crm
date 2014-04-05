package org.ufpr.dac.service;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.ufpr.dac.model.Summary;
import org.ufpr.dac.wrapper.Wrapper;

@SuppressWarnings("unchecked")
public abstract class AbstractServiceHandler<S, W extends Wrapper<S>, ID> {

	private RestTemplate restTemplate;
	private final String path = "http://localhost:8080/dac-crm-services/";
	
	public W getAll(Integer page) {
		return (W) getRestTemplate().getForObject(getPath()+"/page/{page}", Wrapper.class, page);
	}

	public S getOne(ID id) {
		return (S) getRestTemplate().getForObject(getPath()+"{id}", Summary.class, id);
	}
	
	public S create(S model) {
		return (S) getRestTemplate().postForEntity(getPath(), model, Summary.class);
	}
	
	public void update(S model) {
		getRestTemplate().put(getPath(), model);
	}
	
	public void delete(ID id) {
		getRestTemplate().delete(getPath()+"{id}", id);
	}
	
	private RestTemplate getRestTemplate() {
		if(restTemplate == null) {
			restTemplate = new RestTemplate();
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		}
		return restTemplate;
	}

	public String getPath() {
		return path;
	}
	
}
