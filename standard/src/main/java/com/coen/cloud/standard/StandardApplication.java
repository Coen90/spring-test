package com.coen.cloud.standard;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@Log4j2
@SpringBootApplication
public class StandardApplication implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${custom.message}")
    String message;

    public static void main(String[] args) {
        SpringApplication.run(StandardApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.debug("========================================");
        log.debug("========================================");
        log.debug("message={}", message);
        log.debug("========================================");
        log.debug("========================================");
    }
}
