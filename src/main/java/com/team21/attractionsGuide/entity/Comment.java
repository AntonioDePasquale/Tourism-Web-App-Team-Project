package com.team21.attractionsGuide.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.Message;

import java.time.LocalDateTime;
/**
 * This class representing a Comment object
 * @author Antonio De Pasquale, Tong Wu, Hei Lam
 * Date:  06/04/2023
 */

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fields used throughout the class


    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotNull(message = "Rating can't be blank")
    @Min(value = 1,  message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must not larger than 5")
    private Integer rating;

    // The commentDateTime field is set to use the MySQL CURRENT_TIMESTAMP function
    // to ensure that the database server's system time is used instead of the
    // client machine's system time.
    @NotNull
    @Column(name = "comment_date_time", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime commentDateTime;

    @NotBlank(message = "comment can't be blank")
    private String commentText;

    @NotBlank(message = "Location Id can't be blank")
    private String locationId;
    //private Integer age;
    //private String commentTitle;

    /**
     * No-arg constructor - required by JPA.
     * set the commentDateTime field explicitly to ensure that the
     * field is set correctly.when deserialization of JSON POST requests
     */
    public Comment() {
        this.commentDateTime = LocalDateTime.now();
    }

    /**
     * Constructor with arguments
     * 
     * @param name - The name of the person who created the comment
     * @param rating - The numerical rating the comment gives
     * //@param age - The age of the person who created the comment
     * //@param commentTitle - The title of the comment left
     * @param commentText - The main text of the comment left
     * @param locationId - The id correspond of the location
     */
    public Comment(String name, Integer rating, String commentText,String locationId) {
        this.name = name;
        this.rating = rating;
        this.commentText = commentText;
        this.locationId = locationId;
        this.commentDateTime = LocalDateTime.now();
    }

    /**
     * Getters and setters for the fields of the Comment object
     */

    public Long getId() { return id;}
    private void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }


    public LocalDateTime getCommentDateTime() { return commentDateTime; }
    //private void setCommentDateTime(LocalDateTime commentDateTime) { this.commentDateTime = commentDateTime; }


    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }





    /**
     * Override default toString method.
     * @return a string representation of the Comment object
     */
    @Override
    public String toString() {
        return "Comment{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", commentDateTime=" + commentDateTime +
                ", commentText='" + commentText + '\'' +
                ", locationId='" + locationId + '\'' +
                '}';
    }
}
