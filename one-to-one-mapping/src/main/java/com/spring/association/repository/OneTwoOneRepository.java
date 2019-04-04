package com.spring.association.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.association.entity.User;

public interface OneTwoOneRepository extends JpaRepository<User, Long> {

}
