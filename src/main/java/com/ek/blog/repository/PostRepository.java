package com.ek.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ek.blog.entity.Post;
import com.ek.blog.entity.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByReviewed(boolean b);

}
