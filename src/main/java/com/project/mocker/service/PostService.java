package com.project.mocker.service;


import com.project.mocker.dto.PostRequestDto;
import com.project.mocker.model.Post;
import com.project.mocker.repository.PostRepository;
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

