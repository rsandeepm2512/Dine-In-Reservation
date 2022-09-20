package com.aexp.dinein.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TelegramConfig {

    @Value("${telegram.token}")
    private String token;

    @Value("${telegram.baseUrl}")
    private String baseUrl;
}
