package com.team21.attractionsGuide.config;


import com.team21.attractionsGuide.entity.Comment;
import com.team21.attractionsGuide.repository.CommentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Configuration class to insert test comments via CommandLineRunner.
 *              This is a Java class written using Spring framework annotations.
 * Author: Tong Wu
 * Date: 2023/3/25
 */
 
@Configuration  // Annotation indicating that this Java class defines one or more Bean methods
public class CommentConfig {

    /**
     * A Bean method returns an object (the bean) that should be registered as an instance in a Spring Application Context.
     * This particular Bean method is called commandLineRunner, which takes the input of the CommentRepository implementation class and returns an implementation of CommandLineRunner.
     * When the application starts up, this CommandLineRunner will execute its run() method which populates the database with test data.
     */
    @Bean
    CommandLineRunner commandLineRunner(CommentRepository repository) {
        return args -> {
            //creates a list of test comments
            List<Comment> testComments = new ArrayList<>(List.of(
                new Comment("Tony", 5, 5, "my comment", "Hello my name is tony and this place is great!"),
                new Comment("Alex", 1, 21, "don't go here", "Yo this place sucks!")
            ));
    
            try {
                repository.saveAll(testComments); //save all test comments to the CommentRepository
            } catch (Exception e) {
            }
        };
    }
}
