package com.yourcompanyname.forum.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comment extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long commentId;
	
	@NotBlank
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "postId")
	@JsonIgnore
	private Post post;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties("posts")
	private ForumUser commenter;
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public ForumUser getCommenter() {
		return commenter;
	}
	public void setCommenter(ForumUser commenter) {
		this.commenter = commenter;
	}
	
}
