package com.jade.platform.springaiintro.service;

import com.jade.platform.springaiintro.model.Answer;
import com.jade.platform.springaiintro.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.retry.annotation.Retryable;
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

    @Retryable(maxAttempts = 5)
    public Answer getAnswer(Question question) {
        System.out.println("Getting Answer...");
        System.out.println(question.question());
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        System.out.println("Creating Prompt Template");
        Prompt prompt = promptTemplate.create();
        System.out.println("Created Prompt Template");
        ChatResponse chatResponse = chatClient.call(prompt);
        System.out.println("Chant Response now...");

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }
}
