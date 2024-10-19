package com.yourcompanyname.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourcompanyname.forum.entities.Post;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {

}
