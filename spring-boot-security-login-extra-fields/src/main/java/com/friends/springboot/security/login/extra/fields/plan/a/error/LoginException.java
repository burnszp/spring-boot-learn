package com.friends.springboot.security.login.extra.fields.plan.a.error;

import org.springframework.security.core.AuthenticationException;

public  class LoginException  extends AuthenticationException {

    public LoginException(String msg, Throwable t) {
        super(msg, t);
    }

    public LoginException(String msg) {
        super(msg);
    }
}
