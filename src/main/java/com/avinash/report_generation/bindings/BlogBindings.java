package com.avinash.report_generation.bindings;


public class BlogBindings {

	
	public BlogBindings() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BlogBindings(Integer blog_id, String title, String shortDescription, String content, Integer user_id) {
		super();
		this.blog_id = blog_id;
		this.title = title;
		this.shortDescription = shortDescription;
		this.content = content;
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "BlogBindings [blog_id=" + blog_id + ", title=" + title + ", shortDescription=" + shortDescription
				+ ", content=" + content + ", user_id=" + user_id + "]";
	}
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	private Integer blog_id;
	private String title;
	private String shortDescription;
	private String content;
	private Integer user_id;
	
	

}
