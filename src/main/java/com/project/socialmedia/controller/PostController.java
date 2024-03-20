package com.project.socialmedia.controller;

import com.project.socialmedia.dto.PostRequestDto;
import com.project.socialmedia.service.HttpService;
import com.project.socialmedia.service.PostService;
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
            String responseBody = httpService.get("http://worldtimeapi.org/api/timezone/Asia/Kolkata");

            // Return the post ID and outbound HTTP response body
            return ResponseEntity.ok().body(
                    "{\"db_post\": \"" + postId + "\", \"http_outbound\": \"" + responseBody + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage());
        }
    }
}
