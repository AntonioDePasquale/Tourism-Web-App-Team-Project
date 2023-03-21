package com.team21.attractionsGuide.Comments;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CommentConfig {

    //comments are currently hardcoded for database testing purposes
    // TODO make comment config dynamic(make another function for this)

    @Bean
    CommandLineRunner commandLineRunner(CommentRepository repository) {
        return args -> {
            Comment testCommentOne = new Comment(
                    "Tony",
                    5,
                    5,
                    "my comment",
                    "Hello my name is tony and this place is great!"
            );

            Comment testCommentTwo = new Comment(
                    "Alex",
                    1,
                    21,
                    "don't go here",
                    "Yo this place sucks!"
            );

            repository.saveAll(
                    List.of(testCommentOne, testCommentTwo)
            );
        };
    }
}
