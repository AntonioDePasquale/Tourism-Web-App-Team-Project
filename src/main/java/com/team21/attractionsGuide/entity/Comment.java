package com.team21.attractionsGuide.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * A class representing a Comment object
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fields used throughout the class
    private String name;
    private Integer rating;
    private String commentTitle;
    private LocalDateTime commentDateTime;
    private Integer age;
    private String commentText;

    /**
     * No-arg constructor - required by JPA.
     */
    public Comment() {
    }

    /**
     * Constructor with arguments
     * 
     * @param name - The name of the person who created the comment
     * @param rating - The numerical rating the comment gives
     * @param age - The age of the person who created the comment
     * @param commentTitle - The title of the comment left
     * @param commentText - The main text of the comment left
     */
    public Comment(String name, Integer rating, Integer age, String commentTitle, String commentText) {
        this.name = name;
        this.rating = rating;
        this.commentTitle = commentTitle;
        this.commentDateTime = LocalDateTime.now();
        this.age = age;
        this.commentText = commentText;
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

    public String getCommentTitle() { return commentTitle; }
    public void setCommentTitle(String commentTitle) { this.commentTitle = commentTitle; }

    public LocalDateTime getCommentDateTime() { return commentDateTime; }
    private void setCommentDateTime(LocalDateTime commentDateTime) { this.commentDateTime = commentDateTime; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }


    /**
     * Override default toString method.
     * @return a string representation of the Comment object
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", commentTitle=" + commentTitle + '\'' +
                ", commentDateTime=" + commentDateTime + '\'' +
                ", age=" + age + '\'' +
                ", commentText=" + commentText +
                '}';
    }
}
