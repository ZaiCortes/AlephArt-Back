package com.stella.alephart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stella.alephart.models.Comments;
import com.stella.alephart.repository.CommentsRepository;

@Service
public class CommentsService {
	
		@Autowired
		private CommentsRepository commentRepository;
		
		public List<Comments> findAllComments(){
			return commentRepository.findAll();
		}
		
		public Optional<Comments> findCommentsById(Long id) {
			return commentRepository.findById(id);
		}
	
	
		public Comments  saveComment(Comments comment) {
			return commentRepository.save(comment);
		}
		
		
		public Comments updateComment(Long id, Comments comment) {
			Optional<Comments> existingCommentsOptional = commentRepository.findById(id);
			
			if (existingCommentsOptional.isPresent()) {
				Comments existingComments = existingCommentsOptional.get();
			
				 existingComments.setComment_description(comment.getComment_description());
				
				return commentRepository.save(existingComments);
			} else {
				
				throw new RuntimeException("Comentario" + id + " no encontrado");
			}
		
		}
		
		
		public void deleteComment(Long id) {
			commentRepository.deleteById(id);
		}
}
