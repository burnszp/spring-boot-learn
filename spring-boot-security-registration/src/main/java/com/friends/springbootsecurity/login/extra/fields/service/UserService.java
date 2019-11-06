package com.friends.springbootsecurity.login.extra.fields.service;

import com.friends.springbootsecurity.login.extra.fields.dao.UserRepository;
import com.friends.springbootsecurity.login.extra.fields.dao.RoleRepository;
import com.friends.springbootsecurity.login.extra.fields.dto.UserDto;
import com.friends.springbootsecurity.login.extra.fields.error.UserAlreadyExistException;
import com.friends.springbootsecurity.login.extra.fields.plan.a.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        PasswordEncoder x = new BCryptPasswordEncoder();
        String encode = x.encode("123456");
        System.out.println(encode);

    }

    // API

    @Override
    public User registerNewUserAccount(final UserDto accountDto) {
        if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email adress: " + accountDto.getEmail());
        }
        final User user = new User();

        user.setFirstName(accountDto.getFirstName());
        user.setLastName(accountDto.getLastName());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        user.setUsing2FA(accountDto.isUsing2FA());
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }


    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }


}
