package com.project.mocker.controller;

import com.project.mocker.dto.PostRequestDto;
import com.project.mocker.service.HttpService;
import com.project.mocker.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private HttpService httpService;

    @PostMapping("/api/createNewPost")
    public ResponseEntity<?> createNewPost(@RequestBody PostRequestDto requestDto) {
        try {
            Long postId = postService.createNewPost(requestDto);
            String url = System.getenv("HTTP_ENDPOINT");
            String responseBody = httpService.get(url);

            // Return the post ID and outbound HTTP response body
            return ResponseEntity.ok().body(
                    "{\"db_post\": \"" + postId + "\", \"http_outbound\": \"" + responseBody + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }
}
