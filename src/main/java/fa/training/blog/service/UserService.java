package fa.training.blog.service;

import fa.training.blog.entity.UserEntity;
import fa.training.blog.repository.IUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(username);
        if (userEntity == null){
            throw new UsernameNotFoundException(username);
        }
        return new UserPrinciple(userEntity);
    }
}
