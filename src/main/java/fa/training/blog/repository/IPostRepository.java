package fa.training.blog.repository;

import fa.training.blog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface IPostRepository extends JpaRepository<PostEntity, Integer> {
    List<PostEntity> findAllByStatus(int status);
    List<PostEntity> findAllByAuthor_id(int author);
    PostEntity findByAuthor_Id(int authorId);


}
