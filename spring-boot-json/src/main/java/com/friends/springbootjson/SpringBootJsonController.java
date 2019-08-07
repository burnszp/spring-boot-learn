package com.friends.springbootjson;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
public class SpringBootJsonController {

    @PostMapping("testJson")
    public  User  testJson(@RequestBody  User user){
        return user;
    }
}

class  User  {
    private String userAccount;
    private String password;
    private int age;
    private Date birthday;
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
