package com.team21.attractionsGuide.repository;

import com.team21.attractionsGuide.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 * The CommentRepositoryTest is to test the Repository layer of this application
 *
 * @author Hei Lam
 * Date:  06/04/2023
 */
//Create an in-memory database
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TestEntityManager entityManager;

    /**
     *  A setup function that runs before each test case in a test suite
     */
    @BeforeEach
    void setUp() {
        Comment comment = new Comment();
        comment.setName("Tom");
        comment.setRating(1);
        comment.setCommentText("This is a great place!");
        comment.setLocationId("newcastle-1");

        //  Insert the entity to the database if it is not already present, or updates the existing record if it already exists.
        entityManager.persist(comment);
    }

    /**
     * To test the id field is created
     * and comment can be extract from the h2 database
     */
    @Test
    public void whenFindByCommentId_thenReturnComment(){
        String locationId= "newcastle-1";
        Comment comment = commentRepository.findById(1L).get();
        assertEquals(locationId,comment.getLocationId());
    }


}