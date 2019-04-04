package com.zensar.association.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zensar.association.entity.Comment;
import com.zensar.association.entity.Post;
import com.zensar.association.exception.ResourceNotFoundException;
import com.zensar.association.repository.CommentRepository;
import com.zensar.association.repository.PostRepository;

@RestController
public class CommentController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") Long postId, @RequestBody Comment comment) {
    	Post findOne = postRepository.findOne(postId);
    	if(findOne != null) {
    		comment.setPost(findOne);
    		return commentRepository.save(comment);
    	} else {
    		throw new ResourceNotFoundException("PostId " + postId + " not found");
    	}
    	
        /*return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));*/
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @RequestBody Comment commentRequest) {
        
    	if(!postRepository.exists(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
    	Comment findOne = commentRepository.findOne(commentId);
    	if(findOne != null) {
    		findOne.setText(commentRequest.getText());
            return commentRepository.save(findOne);
    	} else {
    		throw new ResourceNotFoundException("PostId " + postId + " not found");
    	}
    	
        /*return commentRepository.findOne(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));*/
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                              @PathVariable (value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }
}
