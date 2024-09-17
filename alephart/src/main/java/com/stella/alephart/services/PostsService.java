package com.stella.alephart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stella.alephart.models.Posts;
import com.stella.alephart.models.User;
import com.stella.alephart.repository.PostsRepository;
import com.stella.alephart.repository.UserRepository;

@Service
public class PostsService {
	
	@Autowired
    private PostsRepository postRepository;
    
	@Autowired
    private UserRepository userRepository;
    
	//GET
	
	public List<Posts> findAllPosts(){
		return postRepository.findAll();
	}
	
	public Optional <Posts> findPostById(Long id){
		return postRepository.findById(id);
	}
	
	//POST
	public Posts savePost(Posts post, Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("El User con el id " + userId + " no fue encontrado"));
        post.setUser(user);
        post.setUserProfile(user.getUserProfile());  // User método getUserProfile()
        return postRepository.save(post);
    }
	
	
	//GET
	//updatedPost es un objeto que contiene los datos actualizados.
	public Posts updatePost(Long id, Posts updatedPost) {
        return postRepository.findById(id)
            .map(currentPost -> {
                currentPost.setPost_description(updatedPost.getPost_description());
                return postRepository.save(currentPost);
            })
            .orElseThrow(() -> new RuntimeException("Publicación con el id " + id + " no encontrada"));
    }
	
	//DELETE
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	

}