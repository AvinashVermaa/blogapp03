package com.avinash.report_generation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.avinash.report_generation.entities.Blog;
import com.avinash.report_generation.repository.BlogRepository;

@Controller
public class AllPostWithCommentsController {

	@Autowired
	private BlogRepository blogRepo;
	
	@GetMapping("/showAllPostWithComments")
	public String showAllPostWithComments(Model model) {
		System.out.println("inside showAllPostWithComments()");
		List<Blog> blogs = blogRepo.findAllBlogsWithComments();
		model.addAttribute("blogs", blogs);
		return "all_posts";
	}
}
