package customer.service;

import customer.dto.UserDto;
import customer.entity.User;
import customer.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userCredentialRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDto(user);
    }
}
