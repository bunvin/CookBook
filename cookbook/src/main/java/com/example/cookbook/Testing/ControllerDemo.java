package com.example.cookbook.Testing;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ControllerDemo implements CommandLineRunner{
    private final RestTemplate restTemplate;
    
//start with Ingredient controller

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
