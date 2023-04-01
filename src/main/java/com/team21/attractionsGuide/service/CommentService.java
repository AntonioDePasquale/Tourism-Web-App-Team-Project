package com.team21.attractionsGuide.service;

import com.team21.attractionsGuide.error.CommentWithTheLocationIdNotFoundException;
import com.team21.attractionsGuide.entity.Comment;
import com.team21.attractionsGuide.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * @return
     */
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Finds a comment by its ID
     * @param id - The ID of the comment to be found
     * @return Optional<Comment>
     */
    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findById(id);
    }



    /**
     * To find all the comments that match the locationId
     * @param locationId
     * @returnall the comments that match the locationId
     * @throws CommentWithTheLocationIdNotFoundException
     */
    public List<Comment> findCommentByLocationId(String locationId) throws CommentWithTheLocationIdNotFoundException {
        List<Comment> comments = commentRepository.findBylocationId(locationId);
        if (comments.isEmpty()) {
            throw new CommentWithTheLocationIdNotFoundException("No comments found for location ID: " + locationId);
        }
        return comments;
    }




    /**
     * Updates a comment with new data
     *
     * @param updatedComment - The updated comment object
     */
    public void updateComment(Comment updatedComment) {
        commentRepository.save(updatedComment);
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
