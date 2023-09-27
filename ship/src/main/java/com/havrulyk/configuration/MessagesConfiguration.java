package com.havrulyk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.havrulyk.common.processor.MessageConverter;

@Configuration
public class MessagesConfiguration {

    @Bean
    public MessageConverter converter() {
        return new MessageConverter();
    }
}
