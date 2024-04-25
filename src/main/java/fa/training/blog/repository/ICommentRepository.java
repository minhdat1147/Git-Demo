package fa.training.blog.repository;

import fa.training.blog.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<CommentEntity,Integer> {
    List<CommentEntity> findAllByPost_Id(int postId);
    List<CommentEntity> findAllByStatusAndPost_Id(int status, int postId);

    @Modifying
    @Query(value = "update CommentEntity  cmt set cmt.status = 2 where cmt.id= :id")
    void approveComment(int id);
}
