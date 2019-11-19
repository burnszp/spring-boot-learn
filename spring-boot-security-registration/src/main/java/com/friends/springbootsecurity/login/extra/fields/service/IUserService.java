package com.friends.springbootsecurity.login.extra.fields.service;

import com.friends.springbootsecurity.login.extra.fields.error.UserAlreadyExistException;
import com.friends.springbootsecurity.login.extra.fields.dto.UserDto;
import com.friends.springbootsecurity.login.model.User;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;

}
