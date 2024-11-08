package com.marcotancredo.cursomc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcotancredo.cursomc.domain.PagamentoComBoleto;
import com.marcotancredo.cursomc.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PagamentoComBoleto.class);
                objectMapper.registerSubtypes(PagamentoComCartao.class);
                super.configure(objectMapper);
            }
        };
    }
}
