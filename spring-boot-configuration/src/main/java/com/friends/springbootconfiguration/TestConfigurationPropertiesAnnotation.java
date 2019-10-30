package com.friends.springbootconfiguration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;
import java.util.*;

@Data
@ConfigurationProperties("acme.wo")
public class TestConfigurationPropertiesAnnotation {

    private boolean enabled;

    private String configStr;

    private Integer configInt;

    private InetAddress remoteAddress ;

    private final Security security = new Security();

    private  MyPojo myPojo ;

    private String[] arrayProps;

    private final List<MyPojo> list = new ArrayList<>();

    private List<String> listProp2 = new ArrayList<>();

    private List<Map<String, String>> listProp1 = new ArrayList<>();

    private Map<String, String> mapProps = new HashMap<>();

    @Data
    public static class MyPojo {
        private String firstName;
        private String lastName;
    }

    @Data
    public static class Security {

        private String username;

        private String password;

        private List<String> roles = new ArrayList<>(Collections.singleton("USER"));
    }
}
