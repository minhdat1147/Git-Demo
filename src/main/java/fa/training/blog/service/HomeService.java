package fa.training.blog.service;

import fa.training.blog.constant.PostStatus;
import fa.training.blog.payload.response.HomeContentResp;
import fa.training.blog.payload.response.PostContentResp;
import fa.training.blog.repository.DBContext;
import fa.training.blog.utility.AuthenticationFacade;
import fa.training.blog.utility.DTOHelpler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {
    @Autowired
    private DBContext dbContext;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public HomeContentResp getHomePublicContent(){
        List<String> tags = DTOHelpler.toTagNames(dbContext.tagRepository.findAll());

        List<PostContentResp> postContentResps = dbContext.postRepository.findAllByStatus(PostStatus.PUBLISHED)
                .stream().map(DTOHelpler :: toDTO).collect(Collectors.toList());
        return HomeContentResp.builder()
                .tags(tags)
                .posts(postContentResps)
                .build();
    }

    public HomeContentResp getAllContentOfAuthor(){
        int authorId = authenticationFacade.getUserId();
        List<String> tags = DTOHelpler.toTagNames(dbContext.tagRepository.findAll());
        List<PostContentResp> postContentResps = dbContext.postRepository.findAllByAuthor_id(authorId)
                .stream().map(DTOHelpler :: toDTO).collect(Collectors.toList());
        return HomeContentResp.builder()
                .tags(tags)
                .posts(postContentResps)
                .build();
    }
}
