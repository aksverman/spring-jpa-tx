package com.rudra.aks.multitx.customer.dao;

import java.util.List;

import com.rudra.aks.multitx.domain.CustomerBO;


public interface CustomerDAO {
	int save(CustomerBO customer);
	List<CustomerBO>	search(String columnName, String searchText);
	void delete(CustomerBO customer);
}
