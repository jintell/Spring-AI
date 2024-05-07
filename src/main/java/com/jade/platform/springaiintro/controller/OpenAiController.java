package com.jade.platform.springaiintro.controller;

import com.jade.platform.springaiintro.model.Answer;
import com.jade.platform.springaiintro.model.GetCapitalRequest;
import com.jade.platform.springaiintro.model.GetPresidentRequest;
import com.jade.platform.springaiintro.model.Question;
import com.jade.platform.springaiintro.service.OpenAiService;
import org.springframework.web.bind.annotation.*;

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
        return openAiService.getAnswer(question);
    }
    @GetMapping("/ai/ask/capital/{stateOrCountry}")
    public Answer askQuestion(@PathVariable String stateOrCountry) {
        return openAiService.getAnswer(new GetCapitalRequest(stateOrCountry));
    }
    @GetMapping("/ai/ask/president/{presidentRequest}")
    public Answer askQuestionAboutPresident(@PathVariable String presidentRequest) {
        return openAiService.getAnswer(new GetPresidentRequest(presidentRequest));
    }
}
