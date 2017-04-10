package com.ek.blog.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ek.blog.repository.UserRepository;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UniqueUser constraintAnnotation) {
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if (userRepository == null)
			return true;
		return userRepository.findByName(username) == null;
	}

}
