package com.zensar.association.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.association.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
