package com.team21.attractionsGuide.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repository for the comments objects to be stored in the database
//extends JpaRepository to get additional functionality
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}


