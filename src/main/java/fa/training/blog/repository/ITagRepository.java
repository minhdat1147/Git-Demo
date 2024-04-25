package fa.training.blog.repository;

import fa.training.blog.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITagRepository extends JpaRepository<TagEntity, Integer> {
    List<TagEntity> findAllByNameIgnoreCaseIn(String[] name);
    TagEntity findByNameIgnoreCase(String name);
}
