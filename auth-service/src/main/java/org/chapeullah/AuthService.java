package org.chapeullah;

import org.chapeullah.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthService {
    public static void main(String[] args) {

        int[] C = new int[4];
        SpringApplication.run(AuthService.class, args);
    }
}