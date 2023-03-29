package com.team21.attractionsGuide.controller;

import com.team21.attractionsGuide.entity.Comment;
import com.team21.attractionsGuide.error.CommentWithTheLocationIdNotFoundException;
import com.team21.attractionsGuide.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MockMvcResultMatchersDsl;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.definedParameter;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    // A Spring Boot test tool class that lets you test controllers without needing to start an HTTP server
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    private Comment comment;

    /**
     *  A setup function that runs before each test case in a test suite
     */
    @BeforeEach
    void setUp() {
        comment = new Comment();
        comment.setName("David");
        comment.setRating(1);
        comment.setCommentText("This is a great place!");
        comment.setLocationId("newcastle-3");
        commentService.createComment(comment);
    }

    /**
     * The get request for all comment should return 200 ok status
     * @throws Exception
     */
    @Test
    void getAllComments() throws Exception {
        List<Comment> comments = Collections.singletonList(comment);

        Mockito.when(commentService.findCommentByLocationId("newcastle-3")).thenReturn(comments);

        mockMvc.perform(get("/comments/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    /**
     * To test the get request , http 200 ok reply
     * and the locationId field form the json
     * which needs to match the comment.getLocationId()
     * @throws Exception
     */
    @Test
    void getRequestOfHttpOkStatus_CommentsBy_LocationId() throws Exception {
        List<Comment> comments = Collections.singletonList(comment);

        Mockito.when(commentService.findCommentByLocationId("newcastle-3")).thenReturn(comments);

        mockMvc.perform(get("/comments/newcastle-3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //check the jsonPath of locationId matches the expected value of comment.getLocationId()
                .andExpect(jsonPath("$.[0].locationId").value(comment.getLocationId()));
    }


    /**
     * To test the CommentWithTheLocationIdNotFoundException will be thrown
     * when the location id request is not found
     * and reply 404 not found correctly
     * @throws Exception
     */
    @Test
    public void testGetCommentsByLocationIdWithNoComments() throws Exception {

        String locationId = "ABC123";

        given(commentService.findCommentByLocationId(locationId))
                .willThrow(new CommentWithTheLocationIdNotFoundException(locationId));

        //expect a 404 Not Found response
        mockMvc.perform(get("/comments/" + locationId))
                .andExpect(status().isNotFound());


    }




    /**
     *  Tests Post request of creating a new comment to the /comments endpoint
     *  using a mock Comment object.
     * @throws Exception if an error occurs during the test
     */
   @Test
    void shouldReturnHttpStatusOkWhenInsertingComment() throws Exception {
        Comment newComment = new Comment();
        newComment.setName("David");
        newComment.setRating(1);
        newComment.setCommentText("This is a great place!");
        newComment.setLocationId("newcastle-3");

        Mockito.when(commentService.createComment(newComment)).thenReturn(comment);

        //The test expects the response status to be HTTP 200 OK.
        mockMvc.perform(post("/comments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"David\",\n" +
                                "\t\t\"rating\": 1,\n" +
                                "\t\t\"commentText\": \"This is a great place!\",\n" +
                                "\t\t\"locationId\": \"newcastle-3\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }


    /**
     * To test the error code of 400 bad request when the rating is below the minimum limited
     * @throws Exception
     */
    @Test
    void givenRatingBelowMinimumRatingLimit_whenCreatingComment_thenReturns400BadRequest() throws Exception {
        Comment newComment = new Comment();
        newComment.setName("David");
        //below min rating requirement of 1
        newComment.setRating(0);
        newComment.setCommentText("This is a great place!");
        newComment.setLocationId("newcastle-3");

        Mockito.when(commentService.createComment(newComment)).thenReturn(comment);

        //The test expects the response status to be HTTP 400 bad request.
        mockMvc.perform(post("/comments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"David\",\n" +
                                "\t\t\"rating\": 0,\n" +
                                "\t\t\"commentText\": \"This is a great place!\",\n" +
                                "\t\t\"locationId\": \"newcastle-3\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }


    /**
     * To test the error code of 400 bad request when the rating is above the max limited
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenRatingAboveMaxRatingLimit_whenCreatingComment_thenReturns400BadRequest() throws Exception {
        Comment newComment = new Comment();
        newComment.setName("David");
        //above max rating requirement of 5
        newComment.setRating(6);
        newComment.setCommentText("This is a great place!");
        newComment.setLocationId("newcastle-3");

        Mockito.when(commentService.createComment(newComment)).thenReturn(comment);

        //The test expects the response status to be HTTP 400 bad request.
        mockMvc.perform(post("/comments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"David\",\n" +
                                "\t\t\"rating\": 6,\n" +
                                "\t\t\"commentText\": \"This is a great place!\",\n" +
                                "\t\t\"locationId\": \"newcastle-3\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }


    /**
     *To test the error code of 400 bad request when the name field is null
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenBlankNameField_whenCreatingComment_thenReturns400BadRequest() throws Exception {
        Comment newComment = new Comment();
        //name is set to null for testing
        newComment.setName("");
        newComment.setRating(1);
        newComment.setCommentText("This is a great place!");
        newComment.setLocationId("newcastle-3");

        Mockito.when(commentService.createComment(newComment)).thenReturn(comment);

        //The test expects the response status to be HTTP 400 bad request.
        mockMvc.perform(post("/comments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"\",\n" +
                                "\t\t\"rating\": 6,\n" +
                                "\t\t\"commentText\": \"This is a great place!\",\n" +
                                "\t\t\"locationId\": \"newcastle-3\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }

    /**
     *To test the error code of 400 bad request when the comment field is null
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenBlankCommentTextField_whenCreatingComment_thenReturns400BadRequest() throws Exception {
        Comment newComment = new Comment();

        newComment.setName("David");
        newComment.setRating(1);
        //  commentText is set to blank
        newComment.setCommentText("");
        newComment.setLocationId("newcastle-3");

        Mockito.when(commentService.createComment(newComment)).thenReturn(comment);

        //The test expects the response status to be HTTP 400 bad request.
        mockMvc.perform(post("/comments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"John\",\n" +
                                "\t\"rating\": 4,\n" +
                                "\t\"commentText\": ,\n" +  // Make commentText blank
                                "\t\"locationId\": \"newcastle-2\"\n" +
                                "}"))
                .andExpect(status().isBadRequest());
    }


    /**
     *To test the error code of 400 bad request when the locationId field is null
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenBlankLocationIdField_whenCreatingComment_thenReturns400BadRequest() throws Exception {
        Comment newComment = new Comment();

        newComment.setName("David");
        newComment.setRating(5);
        newComment.setCommentText("This is a great place!");
        //  locationID is set to blank
        newComment.setLocationId("");

        Mockito.when(commentService.createComment(newComment)).thenReturn(comment);

        //The test expects the response status to be HTTP 400 bad request.
        mockMvc.perform(post("/comments/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"name\": \"\",\n" +
                                "\t\t\"rating\": 5,\n" +
                                "\t\t\"commentText\": \"This is a great place!\",\n" +
                                "\t\t\"locationId\": \"" +
                                "}"))
                .andExpect(status().isBadRequest());
    }



}