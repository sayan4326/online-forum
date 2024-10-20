package com.yourcompanyname.forum.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yourcompanyname.forum.entities.ForumUser;

@Repository
public interface IForumUserRepository extends JpaRepository<ForumUser, Long>{

	public ForumUser  findForumUserByUserName(String userName);
	public ForumUser findForumUserByEmail(String email);

}
