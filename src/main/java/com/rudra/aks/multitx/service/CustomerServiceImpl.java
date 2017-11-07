package com.rudra.aks.multitx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rudra.aks.multitx.domain.CustomerBO;
import com.rudra.aks.multitx.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

		
	@Autowired
	CustomerRepository	repo;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
	public Integer save(CustomerBO customer) {
		int customerid = repo.save(customer).getCustomerid();
		if(customerid >1)
			throw new RuntimeException("custom throw");
		return customerid;
	}

}
