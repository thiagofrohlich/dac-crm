package org.ufpr.dac.wrapper;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.data.domain.Page;

@XmlRootElement
@XmlAccessorType (XmlAccessType.FIELD)
@XmlSeeAlso({OperacaoWrapper.class, PessoaWrapper.class, ProdutoWrapper.class, UsuarioWrapper.class})

public abstract class Wrapper<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long returnedQuantity;
	private Long foundQuantity;
	private Long currentPage;
	private Long totalPages;
	@XmlAnyElement
	@XmlElementWrapper
	private List<T> list;
	
	public Wrapper(){}
	
	public Wrapper(Page<? extends Object> page) {
		returnedQuantity = (long) page.getNumberOfElements();
		foundQuantity = page.getTotalElements();
		currentPage = (long) page.getNumber();
		totalPages = (long) page.getTotalPages();
	}
	public Wrapper(List<T> lista){
		this.list = lista; 
	}
	
	public Long getReturnedQuantity() {
		return returnedQuantity;
	}
	public void setReturnedQuantity(Long returnedQuantity) {
		this.returnedQuantity = returnedQuantity;
	}
	public Long getFoundQuantity() {
		return foundQuantity;
	}
	public void setFoundQuantity(Long foundQuantity) {
		this.foundQuantity = foundQuantity;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
	public Long getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}

}
