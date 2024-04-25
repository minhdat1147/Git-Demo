package fa.training.blog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tbl_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private int status;

    @Column(name = "author")
    private String author;

    @Column(name = "url")
    private String url;

    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private PostEntity post;
}
