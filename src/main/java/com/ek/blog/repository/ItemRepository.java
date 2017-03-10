package com.ek.blog.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ek.blog.entity.Item;
import com.ek.blog.entity.Post;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByPost(Post post, Pageable pageable);
}
