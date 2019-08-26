package com.friends.springbootsecuritybase.service;

import com.friends.springbootsecuritybase.dto.UserDto;
import com.friends.springbootsecuritybase.error.UserAlreadyExistException;
import com.friends.springbootsecuritybase.model.User;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

}
