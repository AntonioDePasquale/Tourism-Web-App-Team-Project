package com.team21.attractionsGuide.service;

import com.team21.attractionsGuide.entity.Comment;
import com.team21.attractionsGuide.error.CommentWithTheLocationIdNotFoundException;
import com.team21.attractionsGuide.repository.CommentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
/**
 * The CommentServiceTest is to test the service layer of this application
 *
 * @author Hei Lam
 * @since  06/04/2023
 */

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // disable Spring's autoconfiguration of the test database
@Transactional // use transactions to roll back database changes after each test
class CommentServiceTest {

    @Autowired
    private CommentService commentService;


    @Autowired
    private CommentRepository commentRepository;

    //Comment instance use in the setup method
    private Comment comment;

    // This method will run before each test
    @BeforeEach
   void setup(){
        comment = new Comment();
        comment.setName("Peter");
        comment.setRating(5);
        comment.setCommentText("This is a great place!");
        comment.setLocationId("newcastle-1");

        // Save the comment object to the comment repository and get the saved instance
        comment = commentRepository.save(comment);

        // Call the createComment method of the comment service, passing in the comment object
        commentService.createComment(comment);
    }

    /**
     * To test the size of the list of comment
     * by using commentRepository.findAll()
     */
    @Test
    void GetAllCommentFromTheDataBase() {
        //in setup() only one comment will be created
        int num=1;
        List<Comment> totalComment = commentRepository.findAll();
        assertEquals(num,totalComment.size());

    }


    @Test
    public void findCommentById() {
        commentService.createComment(comment);
        Long commentId = comment.getId();
        Optional<Comment> result = commentService.findCommentById(commentId);
        assertTrue(result.isPresent());
        assertEquals(comment.getCommentText(), result.get().getCommentText());

         //Or provide a default value in case the Optional is empty
//        Comment defaultValue = new Comment();
//        Comment defaultResult = result.orElse(defaultValue);
//        assertNotNull(defaultResult);
    }

    /**
     *To test the find comment by location id
     * @throws CommentWithTheLocationIdNotFoundException
     */
    @Test
    void findCommentByLocationId() throws CommentWithTheLocationIdNotFoundException {
        String locationId = comment.getLocationId();
        List<Comment> foundComments = commentService.findCommentByLocationId(locationId);

        // Check that all returned comment objects have the correct location ID
        for (Comment foundComment : foundComments) {
            assertEquals(locationId, foundComment.getLocationId());
        }

    }
}