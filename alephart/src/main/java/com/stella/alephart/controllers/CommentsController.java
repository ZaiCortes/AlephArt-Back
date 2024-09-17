package com.stella.alephart.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.stella.alephart.models.Comments;
import com.stella.alephart.services.CommentsService;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
	@Autowired
	private CommentsService commentsService;
	
	
	@GetMapping
	public List<Comments> getAllComments() {
		return commentsService.findAllComments();
	}
	 @GetMapping("/{id}")
	public ResponseEntity<Comments> getCommentsById(@PathVariable("id") Long id) {
		return commentsService.findCommentsById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());}
	 
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<Comments> updateComment(@PathVariable Long id, @RequestBody Comments comment) {
	        try {
	            Comments updatedComment = commentsService.updateComment(id, comment);
	            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	
	 @PostMapping
	    public ResponseEntity<Comments> createComment(
	            @RequestBody Comments comment,
	            @RequestParam Long postId,
	            @RequestParam Long userId) {
	        try {
	            Comments savedComment = commentsService.saveComment(comment, postId, userId);
	            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body(null);
	        }
	    }
	 
	 // localhost:  /api/comments?postId=1&userId=2
	

	@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteComments(@PathVariable("id") Long id) {
			return commentsService.findCommentsById(id)
					.map(comments -> {
						commentsService.deleteComment(id);
						return ResponseEntity.ok().<Void>build();
					})
					.orElse(ResponseEntity.notFound().build());
		}
	
	

}
