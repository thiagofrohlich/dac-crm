package org.ufpr.dac.wrapper;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class Wrapper<T> {
	
	private Long returnedQuantity;
	private Long foundQuantity;
	private Long currentPage;
	private Long totalPages;
	private List<T> list;
	
	public Wrapper(){}
	
	public Wrapper(Page<? extends Object> page) {
		returnedQuantity = (long) page.getNumberOfElements();
		foundQuantity = page.getTotalElements();
		currentPage = (long) page.getNumber();
		totalPages = (long) page.getTotalPages();
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
