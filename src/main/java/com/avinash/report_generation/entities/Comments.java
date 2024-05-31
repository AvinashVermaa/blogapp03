package com.avinash.report_generation.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comments {

	@Id
	@Column(name = "comment_id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "comment", nullable = false, unique = true)
	private String comment;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDate createdOn;

	@UpdateTimestamp
	@Column(insertable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate updatedOn;

	@ManyToOne
	@JoinColumn(name = "blog_id", nullable = false)
	private Blog blog;

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	
	public Comments(Integer commentId, String name, String email, String comment, LocalDate createdOn,
			LocalDate updatedOn, Blog blog) {
		super();
		this.commentId = commentId;
		this.name = name;
		this.email = email;
		this.comment = comment;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.blog = blog;
	}

	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comments [commentId=" + commentId + ", name=" + name + ", email=" + email + ", comment=" + comment
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}

}
