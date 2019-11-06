package com.friends.springbootjpa.service;

import com.friends.springbootjpa.persistence.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    UserEntity getUser(Integer id);

    UserEntity addUser(UserEntity user);

    UserEntity updateUser(UserEntity user);

    Integer deleteUser(Integer id);

    Page<UserEntity> getUsers(Integer pageNum, Integer pageSize);

    Page<UserEntity> viewByPageable(Pageable pageable);

    Page<UserEntity> viewByPageableAndUserEntity(UserEntity userEntity, Pageable pageable);

    Page<UserEntity> viewByPageableAndLastname(String lastname, Pageable pageable);

    UserEntity dynamicUpdate(UserEntity user);
}
