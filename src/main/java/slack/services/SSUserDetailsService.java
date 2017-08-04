package slack.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import slack.models.User;
import slack.repositories.UserRepository;

@Transactional
public class SSUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    public SSUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                System.out.println("user not found with the provided username " + user.toString());
                return null;
            }
            System.out.println(" user from username " + user.toString());
            System.out.println(" user from username " + user.getPassword());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        } catch (Exception e){
            throw new UsernameNotFoundException("User not found");
        }
    }
    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        List<String> role = new ArrayList<String>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        authorities.add(grantedAuthority);
        System.out.println("user authorities are " + authorities.toString());
        return authorities;
    }
}
