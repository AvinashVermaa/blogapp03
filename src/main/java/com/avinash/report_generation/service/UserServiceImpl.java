package com.avinash.report_generation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinash.report_generation.entities.User;
import com.avinash.report_generation.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepo;
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		System.out.println("UserRepository :: constructor");
		this.userRepo = userRepo;
	}

	@Override
	public String saveUserData(User user) {
		logger.info("start saveUserData()");
		System.out.println(user);
		userRepo.save(user);
		logger.info("end-1 saveUserData()");
		return "Something went wrong!!!";
		
	}

}
