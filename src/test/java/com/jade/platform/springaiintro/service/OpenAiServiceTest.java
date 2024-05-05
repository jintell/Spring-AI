package com.jade.platform.springaiintro.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OpenAiServiceTest {

    @Autowired
    OpenAiService openAiService;
    @Test
    void getAnswer() {
        String answer = openAiService.getAnswer("It takes one person 5 hours to dig a 10 foot hole in the " +
                "ground. How long would it take 5 people?" );
        System.out.println("Answer \n"+answer);
    }
}