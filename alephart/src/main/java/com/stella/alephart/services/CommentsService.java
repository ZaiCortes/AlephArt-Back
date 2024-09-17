package com.stella.alephart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stella.alephart.models.Comments;
import com.stella.alephart.models.Posts;
import com.stella.alephart.models.User;
import com.stella.alephart.repository.CommentsRepository;
import com.stella.alephart.repository.PostsRepository;
import com.stella.alephart.repository.UserProfileRepository;
import com.stella.alephart.repository.UserRepository;

@Service
public class CommentsService {
	
		@Autowired
		private CommentsRepository commentRepository;
		
	    @Autowired
	    private PostsRepository postsRepository;

	    @Autowired
	    private UserRepository userRepository;

		
		public List<Comments> findAllComments(){
			return commentRepository.findAll();
		}
		
		public Optional<Comments> findCommentsById(Long id) {
			return commentRepository.findById(id);
		}
	
	
		 public Comments saveComment(Comments comment, Long postId, Long userId) {
		        Posts post = postsRepository.findById(postId)
		            .orElseThrow(() -> new RuntimeException("Post con id " + postId + " no encontrado"));
		        
		        User user = userRepository.findById(userId)
		            .orElseThrow(() -> new RuntimeException("Usuario con id " + userId + " no encontrado"));
		        
		        comment.setPost(post);
		        comment.setUser(user);
		        comment.setUserProfile(user.getUserProfile());  // User método getUserProfile()
		        
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
