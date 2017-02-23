package com.ek.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ek.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
