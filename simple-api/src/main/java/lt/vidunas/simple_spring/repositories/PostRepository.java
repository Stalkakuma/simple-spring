package lt.vidunas.simple_spring.repositories;

import lt.vidunas.simple_spring.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    Optional<Post> findByUsername(String username);
}
