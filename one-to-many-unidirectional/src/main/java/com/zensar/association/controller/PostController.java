package com.zensar.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zensar.association.entity.Post;
import com.zensar.association.exception.ResourceNotFoundException;
import com.zensar.association.repository.PostRepository;

@RestController
public class PostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    public Post createPost( @RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post postRequest) {
    	Post findOne = postRepository.findOne(postId);
    	if(findOne != null) {
    		findOne.setTitle(postRequest.getTitle());
    		findOne.setDescription(postRequest.getDescription());
    		findOne.setContent(postRequest.getContent());
            return postRepository.save(findOne);
    	} else {
    		throw new ResourceNotFoundException("PostId " + postId + " not found");
    	}
        /*return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));*/
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
    	Post findOne = postRepository.findOne(postId);
    	if(findOne != null) {
    		postRepository.delete(findOne);
    		return ResponseEntity.ok().build();
    	} else {
    		throw new ResourceNotFoundException("PostId " + postId + " not found");
    	}
    	
        /*return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));*/
    }
}
