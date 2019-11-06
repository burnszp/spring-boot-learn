package com.friends.springbootsecurity.login.extra.fields.dao;

import com.friends.springbootsecurity.login.extra.fields.plan.a.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Override
    void delete(User user);

}
