package com.itcentercrmquva.quvaitcentercrm.security;
import com.itcentercrmquva.quvaitcentercrm.entity.Users;
import com.itcentercrmquva.quvaitcentercrm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        System.out.println(user.getUsername());
        return  new User(user.getUsername(), user.getPassword(), getRoles(user));
    }
    private Set<SimpleGrantedAuthority> getRoles(Users user){
        return user.getRoles().stream().map(element -> new SimpleGrantedAuthority(element.getName())).collect(Collectors.toSet());
    }



}
