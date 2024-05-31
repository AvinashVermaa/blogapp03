package com.avinash.report_generation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avinash.report_generation.bindings.BlogBindings;
import com.avinash.report_generation.entities.Blog;
import com.avinash.report_generation.entities.Comments;
import com.avinash.report_generation.entities.User;
import com.avinash.report_generation.repository.BlogRepository;
import com.avinash.report_generation.repository.CommentRepository;
import com.avinash.report_generation.repository.UserRepository;

import jakarta.transaction.Transactional;

@Controller
public class BlogController {

	@Autowired
	private BlogRepository blogRepo;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepository commentRepo;

	@PostMapping("saveBlogData")
	public String saveBlogData(@ModelAttribute("blog") BlogBindings bindings, @RequestParam("user_id") String userId,
			Model model) {
		System.out.println(bindings);
		Blog blog = new Blog();
		blog.setContent(bindings.getContent());
		blog.setShortDescription(bindings.getShortDescription());
		blog.setTitle(bindings.getTitle());
		blog.setUser(userRepo.findById(Integer.parseInt(userId)).get());
		
		Blog savedBlog = blogRepo.save(blog);
		System.out.println(savedBlog);
		List<Blog> listBlogs = blogRepo.findByUser(blog.getUser());
		model.addAttribute("listBlogs", listBlogs);
		return "view_user_blog";

	}

	@GetMapping("/edit")
	public String editBlogData(@RequestParam("id") int id, Model model) {
		Optional<Blog> optional = blogRepo.findById(id);
		if (optional.isPresent()) {
			Blog blog = optional.get();
			System.out.println("inside optional.isPresent()");
			model.addAttribute("blog", blog);
			return "edit_blog";
		} else {
			System.out.println("Inside else block");
		}
		return "redirect:/showLoginPage";
	}

	@PostMapping("/editBlogData")
	public String editBlogData(@ModelAttribute("blog") Blog blog, Model model, @RequestParam("blog_id") int blogId) {
		System.out.println("inside editblogdata()");
		System.out.println(blog);
		Blog blog2 = blogRepo.findById(blogId).get();
		blog2.setContent(blog.getContent());
		blog2.setShortDescription(blog.getShortDescription());
		blog2.setTitle(blog.getTitle());
		blogRepo.save(blog2);
		return "redirect:/showLoginPage";
	}

	@GetMapping("/delete")
	public String deleteBlogData(@RequestParam("id") int id, Model model) {
		System.out.println("inside deleteBlogData()");
		blogRepo.deleteById(id);
		Optional<Blog> optional = blogRepo.findById(id);
		if (optional.isPresent()) {
			Blog blog = optional.get();
			List<Blog> lists = blogRepo.findByUser(blog.getUser());
			model.addAttribute("listBlogs", lists);
			return "view_user_blog";
		}
		return "view_user_blog";
	}

}
