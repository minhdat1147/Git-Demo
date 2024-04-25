package fa.training.blog.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Collection;
import java.sql.Date;

@Entity
@Table(name = "tbl_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

//    @Column(name="tags")
//    private String tags;

    @Column(name = "status")
    private  int status;

    @CreationTimestamp
    @Column(name = "create_time",updatable = false)
    private Date createDate;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Date updateDate;

    @ManyToOne()
    @JoinColumn(name="author_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private UserEntity author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CommentEntity> comments;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "tbl_tag_post"
            , joinColumns = @JoinColumn(name="post_id")
            , inverseJoinColumns = @JoinColumn(name="tag_id"))
    private Collection<TagEntity> tags;
}
