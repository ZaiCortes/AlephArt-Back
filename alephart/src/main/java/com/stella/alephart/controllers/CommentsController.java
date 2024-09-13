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
import org.springframework.web.bind.annotation.RestController;


import com.stella.alephart.models.Comments;
import com.stella.alephart.services.CommentsService;

@RestController
@RequestMapping("/api/Comments")
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
	public Comments createComments(@RequestBody Comments comments) {
		return commentsService.saveComment(comments);
	}
	

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
