package com.spring.association.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.association.entity.ShippingAddress;

public interface OneToOneBiDirShippRepository extends JpaRepository<ShippingAddress, Long> {

}
