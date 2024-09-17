package com.stella.alephart.models;


import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="posts")
public class Posts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_posts")
	private Long id_posts;
	
	@Column
	private String post_date;
	
	@Column
	private String post_description;
	
	@Column
	private byte[] post_file;
	
	//FK user_id_user
	//FK user_userprofile_id_user_profile
	
	@ManyToOne
	@JoinColumn(name="user_id_user", nullable = false)
	private User user;
	
	/*
	@ManyToOne
	@JoinColumn(name="user_userprofile_id_user_profile")
	private UserProfile userProfile;
	*/
	
	public Posts() {}

	public Posts(Long id_posts, String post_date, String post_description, byte[] post_file, User user) {
		super();
		this.id_posts = id_posts;
		this.post_date = post_date;
		this.post_description = post_description;
		this.post_file = post_file;
		this.user = user;
	}

	public Long getId_posts() {
		return id_posts;
	}

	public void setId_posts(Long id_posts) {
		this.id_posts = id_posts;
	}

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public String getPost_description() {
		return post_description;
	}

	public void setPost_description(String post_description) {
		this.post_description = post_description;
	}

	public byte[] getPost_file() {
		return post_file;
	}

	public void setPost_file(byte[] post_file) {
		this.post_file = post_file;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Posts [id_posts=" + id_posts + ", post_date=" + post_date + ", post_description=" + post_description
				+ ", post_file=" + Arrays.toString(post_file) + ", user=" + user + "]";
	}
	
	
}