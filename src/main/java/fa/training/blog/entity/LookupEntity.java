package fa.training.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="tbl_lookup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LookupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private int code;

    @Column(name="type")
    private int type;

    @Column(name="position")
    private int position;
}
