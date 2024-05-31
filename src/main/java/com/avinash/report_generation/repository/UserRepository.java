package com.avinash.report_generation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avinash.report_generation.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailAndPassword(String email,String password);
}
