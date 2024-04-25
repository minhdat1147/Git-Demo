package fa.training.blog.utility;

import fa.training.blog.service.UserPrinciple;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.file.attribute.UserPrincipal;

@Component
public class AuthenticationFacade {
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();

    }
    public UserPrinciple getUserPrinciple(){
        return (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
    public Integer getUserId(){
        return getUserPrinciple().getUser().getId();
    }
}
