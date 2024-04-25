package fa.training.blog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="tbl_tag")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="frequency")
    private int frequency;

    @ManyToMany(mappedBy = "tags", fetch=FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<PostEntity> posts;

    public TagEntity(String name){
        this.name = name;
    }

}
