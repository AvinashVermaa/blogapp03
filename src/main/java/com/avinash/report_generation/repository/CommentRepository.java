package com.avinash.report_generation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avinash.report_generation.entities.Blog;
import com.avinash.report_generation.entities.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Integer> {

	public List<Comments> findByBlog(Blog blog);
}
