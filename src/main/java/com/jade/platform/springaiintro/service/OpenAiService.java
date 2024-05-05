package com.jade.platform.springaiintro.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author: Josiah Adetayo
 * @Email: josleke@gmail.com, josiah.adetayo@meld-tech.com
 * @Date: 5/4/24
 */
@Service
public class OpenAiService {

    private final ChatClient chatClient;

    public OpenAiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatClient.call(prompt);

        return chatResponse.getResult().getOutput().getContent();
    }
}
