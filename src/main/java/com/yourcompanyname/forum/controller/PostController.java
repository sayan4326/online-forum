package com.yourcompanyname.forum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompanyname.forum.entities.ForumUser;
import com.yourcompanyname.forum.entities.Post;
import com.yourcompanyname.forum.exception.BusinessException;
import com.yourcompanyname.forum.repo.IForumUserRepository;
import com.yourcompanyname.forum.repo.IPostRepository;

import jakarta.validation.Valid;

@RestController
public class PostController {

	@Autowired
	IPostRepository postRepository;

	@Autowired
	IForumUserRepository userRepository;
	
	@GetMapping(value= { "/api/posts/{postId}","/api/posts"})
	public ResponseEntity<?> getPosts(@PathVariable(required = false) Long postId) {
		List<Post> posts = new ArrayList<>();

		if (postId != null) {
			Optional<Post> optionalPost = postRepository.findById(postId);
			if (optionalPost.isPresent())
				posts.add(optionalPost.get());
		} else
			posts =	postRepository.findAll();
		if (posts == null || posts.isEmpty())
			throw new BusinessException("Post not found.", HttpStatus.NOT_FOUND);

		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}

	@PostMapping("/api/posts")
	public ResponseEntity<?> createPost(@Valid @RequestBody Post post) {
		post.setPostId(null);
		Optional<ForumUser> author = userRepository.findById(post.getAuthor().getUserId());
		if (author.isPresent())
			post.setAuthor(author.get());
		else
			throw new BusinessException("User not found.", HttpStatus.NOT_FOUND);

		Post createdPost = postRepository.save(post);
		return new ResponseEntity<Post>(createdPost, HttpStatus.CREATED);

	}

	@PutMapping("/api/posts/{postId}")
	public ResponseEntity<?> updatePost(@Valid @PathVariable Long postId, @RequestBody Post post) {

		Optional<Post> optionalPost = postRepository.findById(postId);
		if (optionalPost.isPresent()) {
			Post newPost = optionalPost.get();
			newPost.setContent(post.getContent());
			newPost.setTitle(post.getTitle());
			newPost = postRepository.save(newPost);
			return new ResponseEntity<Post>(newPost, HttpStatus.OK);
		} else
			throw new BusinessException("Post not found.", HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Long postId) {

		Optional<Post> optionalPost = postRepository.findById(postId);
		if (optionalPost.isPresent()) {
			postRepository.delete(optionalPost.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			throw new BusinessException("Post not found.", HttpStatus.NOT_FOUND);
	}
}
