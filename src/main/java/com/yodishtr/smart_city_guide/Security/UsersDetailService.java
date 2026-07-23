package com.yodishtr.smart_city_guide.Security;

import com.yodishtr.smart_city_guide.Entities.User;
import com.yodishtr.smart_city_guide.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UsersDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsersDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        UsersDetails userDetails = new UsersDetails(user);
        return userDetails;
    }

}
