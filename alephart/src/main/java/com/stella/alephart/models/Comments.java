package com.stella.alephart.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_comment;
	@Column
	private String comment_date;
	@Column
	private String comment_description;
	
	public Comments() {}
	
	public Comments(Long id_comment, String comment_date, String comment_description) {
		super();
		this.id_comment = id_comment;
		this.comment_date = comment_date;
		this.comment_description = comment_description;
	}

	public Long getId_comment() {
		return id_comment;
	}

	public void setId_comment(Long id_comment) {
		this.id_comment = id_comment;
	}

	public String getComment_date() {
		return comment_date;
	}

	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}

	public String getComment_description() {
		return comment_description;
	}

	public void setComment_description(String comment_description) {
		this.comment_description = comment_description;
	}

	@Override
	public String toString() {
		return "Comments [id_comment=" + id_comment + ", comment_date=" + comment_date + ", comment_description="
				+ comment_description + "]";
	}
	
	
	
	
	

}
