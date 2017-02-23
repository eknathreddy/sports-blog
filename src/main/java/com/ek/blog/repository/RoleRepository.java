package com.ek.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ek.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String string);

}
