package com.friends.springbootjpa.service;

import com.friends.springbootjpa.persistence.dao.UserRepository;
import com.friends.springbootjpa.persistence.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    public UserEntity getUser(Integer id) {
        return userRepository.findById(id).orElse(new UserEntity());
    }

    @Override
    public UserEntity addUser(UserEntity user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public UserEntity updateUser(UserEntity user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public Integer deleteUser(Integer id) {
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public Page<UserEntity> getUsers(Integer pageNum, Integer pageSize) {
        return userRepository.findAll(PageRequest.of(pageNum, pageSize,Sort.by(Sort.Direction.ASC,"lastname")));
        //return userRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Page<UserEntity> viewByPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<UserEntity> viewByPageableAndUserEntity(UserEntity userEntity, Pageable pageable) {
        return userRepository.findByLastname(userEntity.getLastname(),pageable);
    }

    @Override
    public Page<UserEntity> viewByPageableAndLastname(String lastname, Pageable pageable) {
        return userRepository.findByLastname(lastname,pageable);
    }

    @Override
    public UserEntity dynamicUpdate(UserEntity user) {
        userRepository.save(user);
        user.setAddress("new Address");
        userRepository.save(user);
        return user;
    }
}
