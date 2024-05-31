package com.avinash.report_generation.entities;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {

	@Column(name = "blog_id", nullable = false, unique = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blog_id;

	@Column(name = "title", unique = true, nullable = false)
	private String title;

	@Column(name = "short_description", nullable = false, unique = true)
	private String shortDescription;

	@Column(name = "content", nullable = false, unique = true)
	private String content;

	@Override
	public String toString() {
		return "Blog [blog_id=" + blog_id + ", title=" + title + ", shortDescription=" + shortDescription + ", content="
				+ content + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDate createdOn;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@UpdateTimestamp
	@Column(insertable = false)
	private LocalDate updatedOn;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comments> comments;

	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}


	public Blog(String title, String shortDescription, String content, LocalDate createdOn, LocalDate updatedOn,
			User user, List<Comments> comments) {
		super();
		this.title = title;
		this.shortDescription = shortDescription;
		this.content = content;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.user = user;
		this.comments = comments;
	}

	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}

}
