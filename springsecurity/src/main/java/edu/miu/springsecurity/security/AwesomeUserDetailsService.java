package edu.miu.springsecurity.security;

import edu.miu.springsecurity.entity.User;
import edu.miu.springsecurity.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwesomeUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);



        var awesomeUserDetails = new AwesomeUserDetails(user);

        return awesomeUserDetails;
    }
}
