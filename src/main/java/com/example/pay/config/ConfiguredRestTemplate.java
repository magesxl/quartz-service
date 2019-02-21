package com.example.pay.config;

import org.springframework.web.client.RestTemplate;

public class ConfiguredRestTemplate extends RestTemplate {
    public ConfiguredRestTemplate() {
        super();

        RestTemplateUtil.configureRestTemplate(this);
    }
}
