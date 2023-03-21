package com.team21.attractionsGuide.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//maps web request onto the controller class below
@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    private final CommentService commentService;

    //autowired instantiates the commentService variable above and injects it into the controller below
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //maps the commentService getComments method returned values(all the comment objects in the commentRepository).
    @GetMapping
    public List<Comment> getComments() {
        return commentService.getComments();
    }
}
