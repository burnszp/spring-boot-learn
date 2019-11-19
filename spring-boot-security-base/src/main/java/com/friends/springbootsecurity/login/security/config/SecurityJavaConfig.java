package com.friends.springbootsecurity.login.security.config;

import com.friends.springbootsecurity.login.security.component.MyAuthenticationFailureHandler;
import com.friends.springbootsecurity.login.security.component.MySavedRequestAwareAuthenticationSuccessHandler;
import com.friends.springbootsecurity.login.security.component.MySimpleUrlLogoutSuccessHandler;
import com.friends.springbootsecurity.login.security.component.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;
    @Autowired
    private MySimpleUrlLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myFailureHandler ;
    @Override
    public void configure(AuthenticationManagerBuilder web) throws Exception {
        web.inMemoryAuthentication()
                .withUser("admin").password(encoder().encode("adminPass")).roles("ADMIN")
                .and()
                .withUser("user").password(encoder().encode("userPass")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//关闭csrf认证
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)//统一的错误处理
                .and()
                .authorizeRequests()
                .antMatchers("/api/foos/**").authenticated()//foos下接口进行了权限认证后就可以访问了
                .antMatchers("/api/admin/**").hasRole("ADMIN")//此接口需要拥有ADMIN权限的用户才能访问
                .anyRequest().authenticated()//除登陆，登出以及上述外所有请求都将拦截
                .and()//连接符
                .formLogin()//使用表单类型提交鉴权
                //.usernameParameter("user-name")
                //.passwordParameter("pass-word")
                .successHandler(mySuccessHandler)//登陆成功处理
                .failureHandler(myFailureHandler)//登陆失败处理
                .and()
                .logout().logoutSuccessHandler(logoutSuccessHandler);//退出成功处理
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
