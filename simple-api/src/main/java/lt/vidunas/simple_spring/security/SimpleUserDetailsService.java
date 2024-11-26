package lt.vidunas.simple_spring.security;


import lombok.RequiredArgsConstructor;
import lt.vidunas.simple_spring.entities.User;
import lt.vidunas.simple_spring.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SimpleUserDetailsService implements UserDetailsService {

    private final UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return mapUserToSimpleUserDetails(user, authorities);
    }

    private SimpleUserDetails mapUserToSimpleUserDetails(User user, List<SimpleGrantedAuthority> authorities) {
        SimpleUserDetails simpleUserDetails = new SimpleUserDetails();
        simpleUserDetails.setId(user.getId());
        simpleUserDetails.setUsername(user.getUsername());
        simpleUserDetails.setPassword(user.getPassword());
        simpleUserDetails.setEmail(user.getEmail());
        simpleUserDetails.setAuthorities(authorities);
        return simpleUserDetails;
    }

}
