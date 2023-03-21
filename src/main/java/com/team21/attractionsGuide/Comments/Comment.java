package com.team21.attractionsGuide.Comments;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Comment {

    @Id
    @SequenceGenerator(
            name = "Comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence"
    )
    private Long id;
    private String name;
    private Integer rating;
    private String commentTitle;
    private LocalDateTime commentDateTime;
    private Integer age;
    private String commentText;

    public Comment() {
    }

    public Comment(Long id, String name, Integer rating, Integer age, String commentTitle, String commentText) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.commentTitle = commentTitle;
        this.commentDateTime = LocalDateTime.now();
        this.age = age;
        this.commentText = commentText;
    }

    public Comment(String name, Integer rating, Integer age, String commentTitle, String commentText) {
        this.name = name;
        this.rating = rating;
        this.commentTitle = commentTitle;
        this.commentDateTime = LocalDateTime.now();
        this.age = age;
        this.commentText = commentText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCommentDateTime() {return commentDateTime;}

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCommentText() {return commentText;}

    public void setCommentText(String commentText) {this.commentText = commentText;}

    //override of default toString()
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
