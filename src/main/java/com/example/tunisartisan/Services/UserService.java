package com.example.tunisartisan.Services;

import com.example.tunisartisan.Entities.User;
import com.example.tunisartisan.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService, UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("LOGIN ATTEMPT = " + username);
        User user = userRepository.findByUserNameIgnoreCase(username);
        if (user == null) {
            user = userRepository.findByEmailIgnoreCase(username);
        }
        System.out.println("FOUND USER = " + (user != null ? user.getUserName() : "NULL"));
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .authorities(mapAuthorities(user))
                .build();
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return user.getRoles().stream()
                .map(role -> role != null ? role.getRole() : null)
                .filter(r -> r != null && !r.isBlank())
                .map(String::trim)
                .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            user.setIduser(id);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
