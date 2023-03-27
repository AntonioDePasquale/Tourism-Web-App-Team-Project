package com.team21.attractionsGuide.controller;

import com.team21.attractionsGuide.error.CommentWithTheLocationIdNotFoundException;
import com.team21.attractionsGuide.entity.Comment;
import com.team21.attractionsGuide.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CommentController class handles HTTP requests related to comments
 * Date: 2023/3/25
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    /**
     * CommentService is used for handling comment-related business logic.
     */
    private final CommentService commentService;


    /**
     * Constructor, injecting the implementation of CommentService as a dependency.
     * By doing this, we can easily swap the implementation of CommentService without modifying the controller code.
     *
     * @param commentService the implementation of the CommentService to be used
     */
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Handles HTTP GET requests, returning a list of all comment objects.
     * When a client requests the /comments path, this method is called.
     * It uses the injected CommentService instance to fetch the list of comments and returns the list as an HTTP response.
     *
     * @return a list of Comment objects
     */
    @GetMapping("/")
    public List<Comment> getComments() {
        return commentService.getComments();
    }


    /**
     * To return all the comments by location id
     * @param locationId
     * @return all the comments by location
     */
    @GetMapping("/{locationId}")
    public List<Comment> getCommentsById( @PathVariable("locationId") String locationId) throws CommentWithTheLocationIdNotFoundException {
        return commentService.findCommentByLocationId(locationId);
    }

    @PostMapping("/")
    public Comment insertComment(@Valid @RequestBody Comment comment){
         return commentService.createComment(comment);
    }
}
