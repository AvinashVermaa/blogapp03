package com.avinash.report_generation.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avinash.report_generation.bindings.BlogBindings;
import com.avinash.report_generation.entities.Blog;
import com.avinash.report_generation.entities.User;
import com.avinash.report_generation.repository.BlogRepository;
import com.avinash.report_generation.repository.UserRepository;

@Controller
public class LoginController {

	private UserRepository userRepo;
	
	@Autowired
	private BlogRepository blogRepo;

	@Autowired
	public LoginController(UserRepository userRepo) {
		System.out.println("LoginController :: constructor");
		this.userRepo = userRepo;
	}

	Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		logger.info("start/end showLoginPage()");
		return "login";
	}

	@PostMapping("/checkCred")
	public String checkCredData(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model) {
		logger.info("start checkCredData()");
		logger.info("Email : " + email + "-Password : " + password);
		User result = userRepo.findByEmailAndPassword(email, password);
		if (result == null) {
			model.addAttribute("msg", "Invalid Email and Password");
			logger.info("if(result)==null");
			return "login";
		} else if (result != null) {
			logger.info("inside else if(result!=null)");
			BlogBindings binding = new BlogBindings();
			model.addAttribute("user_id", result.getUserId());
			model.addAttribute("blog", binding);
			return "dashboard";
		}
		return "login";
	}
}
