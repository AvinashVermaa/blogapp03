package com.avinash.report_generation.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avinash.report_generation.entities.Blog;
import com.avinash.report_generation.entities.Comments;
import com.avinash.report_generation.entities.User;
import com.avinash.report_generation.repository.BlogRepository;
import com.avinash.report_generation.repository.CommentRepository;
import com.avinash.report_generation.repository.UserRepository;

@Controller
public class ViewBlogPageWithComments {
	
	
	@Autowired
	private BlogRepository blogRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/comment")
	public String showViewBlogPageWithComments(@RequestParam("id") int id,Model model) 
	{
		System.out.println("Inside showViewBlogPageWithComments()");
		System.out.println("Id : "+id);
		Comments comment = new Comments();
		model.addAttribute("comments", comment);
		model.addAttribute("blog_id", id);
		return "view_blog_page_comments";
	}
	
	@PostMapping("/saveCommentData")
	public String saveCommentData(@ModelAttribute("comments") Comments comment,@RequestParam("blog_id") int blogId) {
		System.out.println("Inside saveCommentData()");
		Optional<Blog> optional = blogRepo.findById(blogId);
		
		if(optional.isPresent()) {
			Blog blog = optional.get();
			Comments comment1 = new Comments();
			comment1.setBlog(blog);
			comment1.setComment(comment.getComment());
			comment1.setEmail(comment.getEmail());
			comment1.setName(comment.getName());
			
			//save the comment into db:
			commentRepo.save(comment1);
		}
		
		return "redirect:/showLoginPage";
	}
	
	@GetMapping("checkComment")
	public String checkCommentByLoggedInUser(@RequestParam("id") int id,Model model) {
		List<Comments> listComments = new ArrayList<Comments>();
		System.out.println("inside checkcommentbyloggedinuser()");
		Optional<User> optional = userRepo.findById(id);
		if(optional.isPresent()) {
			User user = optional.get();
			List<Blog> blogs = blogRepo.findByUser(user);
			
			for(Blog blog : blogs) {
				List<Comments> comments = commentRepo.findByBlog(blog);
				for(Comments c1 : comments) {
					if(c1.getCommentId()!=null || c1.getComment()!=null) {
						listComments.add(c1);
					}
				}
			}
			System.out.println(listComments);
			model.addAttribute("listComments", listComments);
		}
		
		
		return "delete_comments";
	}
	
	@GetMapping("deleteComment")
	public String deleteComment(@RequestParam("id") int id,Model model) {
		System.out.println("Inside deleteComment()");
		commentRepo.deleteById(id);
		return "redirect:/showLoginPage";
	}
	
}
