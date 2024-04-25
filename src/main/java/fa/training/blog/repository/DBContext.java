package fa.training.blog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBContext {
    @Autowired
    public IPostRepository postRepository;
    @Autowired
    public ITagRepository tagRepository;
    @Autowired
    public IUserRepository userRepository;
    @Autowired
    public ICommentRepository commentRepository;
}
