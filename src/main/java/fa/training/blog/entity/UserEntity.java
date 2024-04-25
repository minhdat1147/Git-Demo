package fa.training.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "tbl_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name = "salt")
    private String salt;

    @Column(name="email")
    private String email;

    @Column(name = "profile")
    private String profile;

    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<PostEntity> posts;
}
