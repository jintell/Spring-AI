package com.jade.platform.springaiintro.service;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @Author: Josiah Adetayo
 * @Email: josleke@gmail.com, josiah.adetayo@meld-tech.com
 * @Date: 5/7/24
 */

@SpringBootTest
class BaseTestClass {
    @Autowired
    OpenAiChatClient chatClient;

    String chat(String prompt) {
        PromptTemplate template = new PromptTemplate(prompt);
        Prompt promptToSend = template.create();

        return chatClient.call(promptToSend).getResult().getOutput().getContent();
    }
}
