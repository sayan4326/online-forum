package com.yourcompanyname.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourcompanyname.forum.entities.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

}
