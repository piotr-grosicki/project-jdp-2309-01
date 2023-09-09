package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.error.user.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;
    
    public User saveUser(User user) {
        
        return userRepository.save(user);
    }
    
    public User findUserById(Long id) {
        
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
