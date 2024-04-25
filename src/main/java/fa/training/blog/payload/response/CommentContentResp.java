package fa.training.blog.payload.response;

import fa.training.blog.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentContentResp {
    private PostContentResp post;

    private List<CommentEntity> comments;
}
