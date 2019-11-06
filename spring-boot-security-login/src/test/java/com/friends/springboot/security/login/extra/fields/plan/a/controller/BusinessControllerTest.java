package com.friends.springboot.security.login.extra.fields.plan.a.controller;

import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.io.Files.readLines;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class BusinessControllerTest {
    private static TestRestTemplate restTemplate;
    private static HttpHeaders loginHeaders;
    @Before
    public  void before() {
         restTemplate = new TestRestTemplate();
    }

    public  void login(MultiValueMap<String, String> form) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        ResponseEntity<String> entity = restTemplate.exchange("http://localhost:8081/login", HttpMethod.POST,
                new HttpEntity<>(form, headers), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        loginHeaders  = entity.getHeaders();
        assertThat(loginHeaders.get("Set-Cookie")).isNotNull();
        List<String> SetCookieStrings = loginHeaders.get("Set-Cookie");
        String joinHeader = StringUtils.join(SetCookieStrings, ";");
        loginHeaders = new HttpHeaders();
        loginHeaders.set("Cookie",joinHeader);

        List<String> jsessionid = SetCookieStrings.stream().filter(x -> x.contains("JSESSIONID") || x.contains("remember-me")).collect(Collectors.toList());
        String join = StringUtils.join(jsessionid, "\n");
        System.out.println(join);
        try {
            Files.write(join.getBytes(),new File("cookie.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void notLogin() {
        ResponseEntity<String> entity = this.restTemplate.exchange("http://localhost:8081/api/user/1", HttpMethod.DELETE,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void noHasDeleteUserAuthority() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("username", "test2@test.com");
        form.set("password", "123456");
        login(form);
        ResponseEntity<String> entity = this.restTemplate.exchange("http://localhost:8081/api/user/1", HttpMethod.DELETE,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }


    @Test
    public void login() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.set("username", "test@test.com");
        form.set("password", "123456");
        form.set("remember-me", "true");
        login(form);
        ResponseEntity<String> entity = this.restTemplate.exchange("http://localhost:8081/api/user/1", HttpMethod.DELETE,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void deleteOneMinuteLaterByJsessionId() throws InterruptedException, IOException {
        HttpHeaders loginHeaders = getHttpHeaders(1);
        //sleep one minute until session expired
        Thread.sleep(60000L);
        ResponseEntity<String> entity = this.restTemplate.exchange("http://localhost:8081/api/user/1", HttpMethod.DELETE,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void deleteByRememberMeCookie() throws IOException {
        HttpHeaders loginHeaders = getHttpHeaders(0);
        ResponseEntity<String> entity = this.restTemplate.exchange("http://localhost:8081/api/user/1", HttpMethod.DELETE,
                new HttpEntity<Void>(loginHeaders), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    private HttpHeaders getHttpHeaders(Integer line) throws IOException {
        HttpHeaders loginHeaders = new HttpHeaders();
        List<String> strings = readLines(new File("cookie.txt"), Charset.forName("UTF-8"));
        loginHeaders.set("Cookie",strings.get(line));
        return loginHeaders;
    }


}