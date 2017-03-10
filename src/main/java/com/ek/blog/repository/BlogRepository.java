package com.ek.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ek.blog.entity.Blog;
import com.ek.blog.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);

}
