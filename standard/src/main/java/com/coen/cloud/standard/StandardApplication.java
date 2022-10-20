package com.coen.cloud.standard;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.nio.charset.Charset;

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

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonConverter.setObjectMapper(om);
        jsonConverter.setDefaultCharset(Charset.forName("UTF-8"));
        return jsonConverter;
    }
}
