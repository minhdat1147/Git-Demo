package fa.training.blog.repository;

import fa.training.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserName(String username);
}
