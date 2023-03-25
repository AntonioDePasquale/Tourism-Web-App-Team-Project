package com.team21.attractionsGuide.service;

import com.team21.attractionsGuide.repository.CommentRepository;
import com.team21.attractionsGuide.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing Comment objects.
 */
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    /**
     * Autowired instantiates the commentRepository variable above and injects it into the service below
     *
     * @param commentRepository - The repository to be injected
     */
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Gets a list of all comments in the repository
     * @return List<Comment>
     */
    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    /**
     * Takes data from a frontend form and formats it into a comment object, then adds to the repository
     *
     * @param comment- The comment object to be added
     */
    public void createComment(Comment comment) {
        commentRepository.save(comment);
    }

    /**
     * Deletes a comment based on its ID
     *
     * @param id - The ID of the comment to be deleted
     */
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
