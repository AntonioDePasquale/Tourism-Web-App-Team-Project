package com.team21.attractionsGuide.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    //autowired lets the CommentService class instantiate itself to provide access to the repository
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //findAll() returns a list of all comments in the repository
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void createComment() {
        // TODO write a function that takes data from a frontend form and format it into a comment object
        //parameters to invoke the Comment constructor
        // add created comment to the repository
       // return type Comment;
    };

    public void deleteComment() {
        // TODO write a function that allows a comment to be deleted
        //  (comments have a unique id that can be used)
    }
}
