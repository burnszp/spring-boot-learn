package com.friends.springbootsecurity.login.extra.fields.dao;

import com.friends.springbootsecurity.login.extra.fields.plan.a.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
