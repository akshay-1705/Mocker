package com.project.mocker.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contents;

    public Post() {
    }
    public Post(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    // Getter for id
    public Long getId() {
        return id;
    }
}

