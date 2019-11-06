package com.friends.springbootsecurity.login.extra.fields.plan.a.security.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository("userRepository")
public class SimpleUserRepository implements UserRepository {

    @Override
    public User findUser(String username, String domain) {
        if (StringUtils.isAnyBlank(username, domain)) {
            return null;
        } else {
            Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
            User user =  new User(username, domain, 
                "$2a$10$U3GhSMpsMSOE8Kqsbn58/edxDBKlVuYMh7qk/7ErApYFjJzi2VG5K", true, 
                true, true, true, authorities);
            return user;
        }
    }

}
