package com.friends.springbootsecurity.login.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@EnableWebSecurity
public class SimpleSecurityJavaConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder web) throws Exception {
        //使用内存管理器提供用户信息
        web.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
                .and()
                .withUser("user").password(encoder().encode("userPass")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//关闭csrf认证
                .authorizeRequests()//配置请求接口权限开始
                .antMatchers("/api/foos/**").authenticated()//foos下接口进行了权限认证后就可以访问了
                .antMatchers("/api/admin/**").hasRole("ADMIN")//此接口需要拥有ADMIN权限的用户才能访问
                .and()//连接符
                .formLogin();//使用表单类型提交鉴权
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
