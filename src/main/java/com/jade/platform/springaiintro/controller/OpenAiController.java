package com.jade.platform.springaiintro.controller;

import com.jade.platform.springaiintro.model.Answer;
import com.jade.platform.springaiintro.model.Question;
import com.jade.platform.springaiintro.service.OpenAiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Josiah Adetayo
 * @Email: josleke@gmail.com, josiah.adetayo@meld-tech.com
 * @Date: 5/6/24
 */
@RestController
public class OpenAiController {

    private final OpenAiService openAiService;

    public OpenAiController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }
    @PostMapping("/ai/ask")
    public Answer askQuestion(@RequestBody Question question) {
        System.out.println(question);
        return openAiService.getAnswer(question);
    }
}
