package lt.vidunas.simple_spring.services;

import lombok.RequiredArgsConstructor;
import lt.vidunas.simple_spring.entities.Post;
import lt.vidunas.simple_spring.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

//    public Optional<Post> findByAuthor(String username) {
//        return postRepository.findByUsername(username);
//    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    public Post updatePost(Long id, Post post) {
        Post updatedPost = postRepository.findById(id).get();
        updatedPost.setTitle(post.getTitle());
        updatedPost.setContent(post.getContent());
        return updatedPost;
    }
}
