package com.friends.springbootsecurity.login.extra.fields.plan.a.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders loginHeaders;
    public void login( MultiValueMap<String, String> form) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> entity = this.restTemplate.exchange("/login", HttpMethod.POST,
                new HttpEntity<>(form, headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        loginHeaders  = entity.getHeaders();
        assertThat(loginHeaders.get("Set-Cookie")).isNotNull();
        List<String> strings = loginHeaders.get("Set-Cookie");
        loginHeaders = new HttpHeaders();
        loginHeaders.set("Cookie",strings.get(0));
    }

    @Test
    public void testNotLogin() {
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/admin/hi", HttpMethod.GET,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void testHiNotHaveAdminRole() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("username", "user");
        form.set("password", "userPass");
        login(form);
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/admin/hi", HttpMethod.GET,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void testHiHaveAdminRole() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("username", "admin");
        form.set("password", "adminPass");
        login(form);
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/admin/hi", HttpMethod.GET,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertEquals("hey guys!",entity.getBody());
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testLogout() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("username", "admin");
        form.set("password", "adminPass");
        login(form);
        ResponseEntity<String> entity = this.restTemplate.exchange("/logout", HttpMethod.GET,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}