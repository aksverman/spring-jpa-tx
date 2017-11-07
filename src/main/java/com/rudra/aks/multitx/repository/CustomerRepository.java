package com.rudra.aks.multitx.repository;

import org.springframework.data.repository.CrudRepository;

import com.rudra.aks.multitx.domain.CustomerBO;

public interface CustomerRepository extends CrudRepository<CustomerBO, Integer>{

}
