package com.example.digitalmindwebservices.controller;

import com.example.digitalmindwebservices.entities.Post;
import com.example.digitalmindwebservices.service.IPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/posts")
@Api(tags = "Users", value = "Web Service RESTFul of Posts")
public class PostController {
    private final IPostService postService;
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "List all Posts", notes = "Method to list all Posts")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All Posts founds"),
            @ApiResponse(code = 404, message = "Posts Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<List<Post>> findAllPosts(){
        try {
            List<Post> posts = postService.getAll();
            if(posts.size()>0)
                return new ResponseEntity<>(posts, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Search Post by Id", notes = "Method for find a Post by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Post found by Id"),
            @ApiResponse(code = 404, message = "Post Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Post> findById (@PathVariable("id") Long id){
        try {
            Optional<Post> post = postService.getById(id);
            if(post == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<>(post.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Post", notes = "Method for create a Post")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Post created"),
            @ApiResponse(code = 404, message = "Post Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        try {
            Post newPost = postService.save(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update Post", notes = "Method for update a Post")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Post updated"),
            @ApiResponse(code = 404, message = "Post Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @Valid @RequestBody Post post){
        try {
            Optional<Post> postFound = postService.getById(id);
            if(!postFound.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                post.setId(id);
                postService.save(post);
                return new ResponseEntity<>(post, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete Post", notes = "Method for delete a Post")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Post deleted"),
            @ApiResponse(code = 404, message = "Post Not Found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<Post> deletePost(@PathVariable("id") Long id){
        try {
            Optional<Post> postFound = postService.getById(id);
            if(!postFound.isPresent())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else {
                postService.delete(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
