package com.stella.alephart.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stella.alephart.models.Posts;
import com.stella.alephart.models.UserProfile;
import com.stella.alephart.repository.PostsRepository;
import com.stella.alephart.repository.UserProfileRepository;

@Service
public class PostsService {
	
	@Autowired
    private PostsRepository postRepository;
    
    @Autowired
    private UserProfileRepository userProfileRepository;
    
    public Posts savePost(Posts post, Long userProfileId) {
        UserProfile userProfile = userProfileRepository.findById(userProfileId)
            .orElseThrow(() -> new RuntimeException("El UserProfile con el id " + userProfileId + " no fue encontrado"));
        post.setUserProfile(userProfile);
        return postRepository.save(post);
    }
	
	public List<Posts> findAllPosts(){
		return postRepository.findAll();
	}
	
	public Optional <Posts> findPostById(Long id){
		return postRepository.findById(id);
	}
	
	//updatedPost es un objeto que contiene los datos actualizados.
	public Posts updatePost(Long id, Posts updatedPost) {
		Optional<Posts> postOptional = postRepository.findById(id);
		if(postOptional.isPresent()) {
			Posts currentPost = postOptional.get();
			currentPost.setPost_description(updatedPost.getPost_description());
			return postRepository.save(currentPost);
		} else {
			throw new RuntimeException("Publicación con el id " + id + " no encontrada");
		}
		
	}
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}
	

}