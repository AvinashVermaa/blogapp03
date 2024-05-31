package com.avinash.report_generation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.avinash.report_generation.entities.User;
import com.avinash.report_generation.service.UserService;

@Controller
public class RegistrationController {
	
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(RegistrationController.class);
	
	@Autowired
	public RegistrationController(UserService userService) {
		System.out.println("RegistrationController :: constructor");
		this.userService = userService;
	}

	@GetMapping("/showRegPage")
	public String showRegPage(Model model) {
		logger.info("start showRegPage() : ");
		User user = new User();
		model.addAttribute("user", user);
		logger.info("end-1 showRegPage()");
		return "registration";
	}
	
	@PostMapping("/saveUserData")
	public String saveUserData(@ModelAttribute("user") User user,Model model) {
		logger.info("start saveUserData()");
		System.out.println(user);
		String msg = userService.saveUserData(user);
		User user1 = new User();
		model.addAttribute("user1", user1);
		model.addAttribute("msg",msg);
		logger.info("end-1 saveUserData()");
		return "registration";
	}
}
