package com.shopping_app_next.authapi_1.service;

import com.shopping_app_next.authapi_1.entity.User;
import com.shopping_app_next.authapi_1.exception.UserAlreadyExistsException;
import com.shopping_app_next.authapi_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) throws UserAlreadyExistsException{
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        if(findUser.isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        return userRepository.save(user);
    }

    public User validateUser(String email, String password) throws UserAlreadyExistsException {
        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new UserAlreadyExistsException("User not found"));
    }

}
