package com.friends.springboot.security.login.extra.fields.plan.a.dao;

import com.friends.springboot.security.login.extra.fields.plan.a.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
