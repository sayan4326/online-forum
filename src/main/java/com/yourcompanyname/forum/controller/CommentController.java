package com.yourcompanyname.forum.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yourcompanyname.forum.entities.Comment;
import com.yourcompanyname.forum.entities.Post;
import com.yourcompanyname.forum.exception.BusinessException;
import com.yourcompanyname.forum.repo.ICommentRepository;
import com.yourcompanyname.forum.repo.IPostRepository;

@RestController
public class CommentController {
	@Autowired
	IPostRepository postRepository;
	
	@Autowired
	ICommentRepository commentRepository;

	@PostMapping("/api/posts/{postId}/comments")
	public ResponseEntity<?> createComment(@PathVariable Long postId ,@RequestBody Comment comment) {
		Optional<Post> optionalPost = postRepository.findById(postId);
		if (optionalPost.isPresent()) {
			comment.setPost(optionalPost.get());
			Comment savedComment = commentRepository.save(comment);
			return new ResponseEntity<Comment>(savedComment, HttpStatus.CREATED);
		} else
			throw new BusinessException("Post not found.", HttpStatus.NOT_FOUND);
	}

	@PutMapping("/api/comments/{commentId}")
	public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestBody Comment comment) {

		Optional<Comment> optionalComment = commentRepository.findById(commentId);
		if (optionalComment.isPresent()) {
			Comment updatedComment = optionalComment.get();
			updatedComment.setContent(comment.getContent());
			updatedComment = commentRepository.save(updatedComment);
			return new ResponseEntity<Comment>(updatedComment, HttpStatus.OK);
		} else
			throw new BusinessException("Comment not found.", HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/api/comments/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
		Optional<Comment> optionalComment = commentRepository.findById(commentId);

		if (optionalComment.isPresent()) {
			Comment commentToDelete = optionalComment.get();
			commentRepository.delete(commentToDelete);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else
			throw new BusinessException("Comment not found.", HttpStatus.NOT_FOUND);
	}

}
