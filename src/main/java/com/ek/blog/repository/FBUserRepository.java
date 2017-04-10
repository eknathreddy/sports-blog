package com.ek.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ek.blog.entity.FBUser;
import com.ek.blog.entity.User;

public interface FBUserRepository extends JpaRepository<FBUser, String> {

	User findByName(String name);

}
