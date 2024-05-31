package com.avinash.report_generation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avinash.report_generation.entities.Blog;
import com.avinash.report_generation.entities.User;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

	public List<Blog> findByUser(User user);
	
	@Query("SELECT b FROM Blog b JOIN FETCH b.comments")
    List<Blog> findAllBlogsWithComments();
}
