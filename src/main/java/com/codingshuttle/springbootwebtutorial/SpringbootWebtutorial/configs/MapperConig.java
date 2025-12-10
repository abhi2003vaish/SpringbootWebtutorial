package com.codingshuttle.springbootwebtutorial.SpringbootWebtutorial.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConig {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }


}
