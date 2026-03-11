package io.github.ivancarlosantos.bean_validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;

@Slf4j
@SpringBootApplication
public class BeanValidationApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeanValidationApplication.class, args);
        BeanValidationApplication.log.info("BeanValidationApplication Started UP {}", HttpStatus.OK);
    }
}
