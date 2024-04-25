package fa.training.blog.service;

import fa.training.blog.entity.PostEntity;
import fa.training.blog.entity.TagEntity;
import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.repository.DBContext;
import fa.training.blog.utility.AuthenticationFacade;
import fa.training.blog.utility.DTOHelpler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private DBContext dbContext;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public PostEntity createPost(PostContentReq postContentReq){
        List<String> tags = Arrays.stream(postContentReq.getTags().split(",")).map(String::trim).collect(Collectors.toList());
        List<TagEntity> tagEntityList = getTagListInDBAndSaveIfNotExist(tags);
        PostEntity postEntity = PostEntity.builder()
                .id(postContentReq.getId())
                .title(postContentReq.getTitle())
                .content(postContentReq.getContent())
                .status(postContentReq.getStatus())
                .tags(tagEntityList)
                .author(authenticationFacade.getUserPrinciple().getUser())
                .build();
        return (PostEntity) dbContext.postRepository.save(postEntity);

    }

    private List<TagEntity> getTagListInDBAndSaveIfNotExist(List<String> tags){
        List<TagEntity> tagEntityList = new ArrayList<>();
        TagEntity tagEntity = null;
        for (String tagName : tags){
            tagEntity = dbContext.tagRepository.findByNameIgnoreCase(tagName);
            if (tagEntity == null){
                tagEntity = (TagEntity) dbContext.tagRepository.save(new TagEntity(tagName));
            }
            tagEntityList.add(tagEntity);
        }
        return tagEntityList;
    }

    public boolean deletePost(int postId){
        int authorId = authenticationFacade.getUserId();
        PostEntity postEntity = getPostByIdAndAuthorId(authorId);
        if (postEntity == null){
            return false;
        }
        dbContext.postRepository.delete(postEntity);
        return true;
    }

    private PostEntity getPostByIdAndAuthorId(int authorId){
        return dbContext.postRepository.findByAuthor_Id( authorId);
    }

    public PostContentReq getPostContentReqByIdAndAuthorId(int postId){
        int authorId = authenticationFacade.getUserId();
        PostEntity postEntity = getPostByIdAndAuthorId( authorId);
        if (postEntity !=null){
            return DTOHelpler.toDTOReq(getPostByIdAndAuthorId(authorId));
        }
        return new PostContentReq();
    }
}
