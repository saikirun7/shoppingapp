package com.shopping_app_next.authapi_1.service;

import com.shopping_app_next.authapi_1.entity.User;
import com.shopping_app_next.authapi_1.exception.UserAlreadyExistsException;

public interface UserService {
    User addUser(User user) throws UserAlreadyExistsException;
    User validateUser(String email, String password) throws UserAlreadyExistsException;
}
