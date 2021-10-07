package com.welog.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.welog.www.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
