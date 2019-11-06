package com.friends.springbootjpa.persistence.dao;

import com.friends.springbootjpa.persistence.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    Page<UserEntity> findByLastname(String lastname, Pageable pageable);
}
