package com.team21.attractionsGuide.repository;

import com.team21.attractionsGuide.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description: Repository for the Comment objects to be stored in the database.
 * Extends JpaRepository to get additional functionality.
 *
 * Date: 2023/3/25
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findBylocationId(String locationID);
}


