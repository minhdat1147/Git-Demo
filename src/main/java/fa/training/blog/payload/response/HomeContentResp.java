package fa.training.blog.payload.response;

import fa.training.blog.payload.request.PostContentReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeContentResp {
    private List<PostContentResp> posts;
    private List<String> tags;
}
