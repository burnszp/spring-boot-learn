package com.friends.springbootsecurity.login.extra.fields.plan.a.security.filter;

public interface UserRepository {

    public User findUser(String username, String domain);
    
}
