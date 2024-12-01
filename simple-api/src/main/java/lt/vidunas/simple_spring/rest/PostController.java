package lt.vidunas.simple_spring.rest;


import lombok.RequiredArgsConstructor;
import lt.vidunas.simple_spring.entities.Post;
import lt.vidunas.simple_spring.entities.User;
import lt.vidunas.simple_spring.services.PostService;
import lt.vidunas.simple_spring.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{username}")
    public ResponseEntity<Post> createPost(@PathVariable String username, @RequestBody Post post) {
        Optional<User> user = userService.getUserByUsername(username);
        post.setUser(user.get());
        postService.savePost(post);
        return ResponseEntity.ok(post);
    }
}
