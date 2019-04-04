package com.spring.association.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.association.entity.Customer;

public interface OneToOneBiDirCustRepository extends JpaRepository<Customer, Long> {

}
