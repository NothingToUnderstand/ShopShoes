package com.viktor.task.shoes.manager.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages={"com.viktor.task.shoes.manager.web"}
)
//@ComponentScan(basePackages = {"com.viktor.task.shoes.manager.web"})
@EnableJpaRepositories(basePackages = {"com.viktor.task.shoes.manager.web"})
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
