package com.friends.springboot.security.login.extra.fields.plan.a.security.config;

import com.friends.springboot.security.login.extra.fields.plan.a.security.component.RestAuthenticationEntryPoint;
import com.friends.springboot.security.login.extra.fields.plan.a.service.MyUserDetailsService;
import com.friends.springboot.security.login.extra.fields.plan.a.security.component.MySavedRequestAwareAuthenticationSuccessHandler;
import com.friends.springboot.security.login.extra.fields.plan.a.security.component.MySimpleUrlLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;
    @Autowired
    private MySimpleUrlLogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private MyUserDetailsService userDetailsService;
    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .and()
                .formLogin()
                .successHandler(mySuccessHandler)
                .failureHandler(myFailureHandler)
                .and()
                .logout().logoutSuccessHandler(logoutSuccessHandler)
                .and().rememberMe().tokenValiditySeconds(60*60*24);
    }
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
