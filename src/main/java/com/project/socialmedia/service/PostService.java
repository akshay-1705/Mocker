package com.project.socialmedia.service;


import com.project.socialmedia.dto.PostRequestDto;
import com.project.socialmedia.model.Post;
import com.project.socialmedia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Long createNewPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto.getName(), requestDto.getContents());
        postRepository.save(post);
        // For simplicity, return post ID directly
        return post.getId();
    }
}

