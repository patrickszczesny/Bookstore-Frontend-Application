package com.example.bookstorefrontend.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDeatailsServiceImp implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        return userRepository.findByUserName(userName).map(
                user->{
                    Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
                            .collect(Collectors.toSet());
                    return new org.springframework.security.core.userdetails
                            .User(userName,user.getPassword(),grantedAuthorities);

        }).orElseThrow(()->new UsernameNotFoundException("Login failed for user "+ userName));
    }
}
