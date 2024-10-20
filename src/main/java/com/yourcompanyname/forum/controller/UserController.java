package com.yourcompanyname.forum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompanyname.forum.entities.ForumUser;
import com.yourcompanyname.forum.exception.BusinessException;
import com.yourcompanyname.forum.repo.IForumUserRepository;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	IForumUserRepository userRepository;

	@GetMapping(value = { "/api/users/{userId}", "/api/users" })
	public ResponseEntity<?> getUser(@PathVariable(required = false) Long userId) {

		List<ForumUser> forumUsers = new ArrayList<>();
		if (userId != null) {
			Optional<ForumUser> optionalForumUser = userRepository.findById(userId);
			if (optionalForumUser.isPresent())
				forumUsers.add(optionalForumUser.get());
		} else
			forumUsers = userRepository.findAll();

		if (forumUsers != null && !forumUsers.isEmpty())
			return new ResponseEntity<List<ForumUser>>(forumUsers, HttpStatus.FOUND);
		else
			throw new BusinessException("User Not Found", HttpStatus.NOT_FOUND);
	}

	@PostMapping("/api/auth/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody ForumUser user) {
		user.setUserId(null);
		ForumUser forumUserByUserName = userRepository.findForumUserByUserName(user.getUserName());
		if (forumUserByUserName != null)
			throw new BusinessException("User Name Already Taken", HttpStatus.CONFLICT);

		ForumUser forumUserByEmail = userRepository.findForumUserByEmail(user.getUserName());
		if (forumUserByEmail != null)
			throw new BusinessException("User already exists", HttpStatus.CONFLICT);

		ForumUser createdUser = userRepository.save(user);

		return new ResponseEntity<ForumUser>(createdUser, HttpStatus.CREATED);
	}

	public ResponseEntity<?> loginUser() {
		return null;
	}

}
