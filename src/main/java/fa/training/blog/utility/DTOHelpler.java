package fa.training.blog.utility;

import fa.training.blog.constant.CommentStatus;
import fa.training.blog.constant.PostStatus;
import fa.training.blog.entity.PostEntity;
import fa.training.blog.entity.TagEntity;
import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.payload.response.PostContentResp;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DTOHelpler {
    public static PostContentResp toDTO(PostEntity postEntity){
        PostContentResp resp = PostContentResp.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .createTime(postEntity.getCreateDate())
                .updateTime(postEntity.getUpdateDate())
                .authorName(postEntity.getAuthor().getUserName())
                .tags(toTagNames(postEntity.getTags()))
                .amountComment(Math.toIntExact(postEntity.getComments().stream().filter(commentEntity -> commentEntity.getStatus() == CommentStatus.APPROVE).count()))
                .status(getStatus(postEntity.getStatus()))
                .build();
        return resp;
    }

    public static List<String> toTagNames(Collection<TagEntity> tags){
        return tags.stream().map(TagEntity::getName).collect(Collectors.toList());

    }

    public static String getStatus(int status){
        if (status == PostStatus.DRAFT) return  "draft";

        if (status == PostStatus.PUBLISHED) return "published";
        if (status == PostStatus.OUTDATED) return "outdate";
        return "";
    }

    public static PostContentReq toDTOReq(PostEntity postEntity){
        return PostContentReq.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .status(postEntity.getStatus())
                .tags(postEntity.getTags().stream().map(TagEntity::getName).collect(Collectors.joining(", ")))
                .build();
    }
}
