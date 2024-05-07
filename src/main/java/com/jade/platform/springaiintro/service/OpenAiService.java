package com.jade.platform.springaiintro.service;

import com.jade.platform.springaiintro.model.Answer;
import com.jade.platform.springaiintro.model.GetCapitalRequest;
import com.jade.platform.springaiintro.model.GetPresidentRequest;
import com.jade.platform.springaiintro.model.Question;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Value("classpath:template/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:template/get-president-prompt.st")
    private Resource getPresidentPrompt;

    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatClient.call(prompt);

        return chatResponse.getResult().getOutput().getContent();
    }


    public Answer getAnswer(Question question) {
        System.out.println(question.question());
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatClient.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

    public Answer getAnswer(GetCapitalRequest capitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry())); // This is binding
        ChatResponse chatResponse = chatClient.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

    public Answer getAnswer(GetPresidentRequest presidentRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getPresidentPrompt);
        Prompt prompt = promptTemplate.create(Map.of("presidentOrCompany", presidentRequest.presidentOrCompany())); // This is binding
        ChatResponse chatResponse = chatClient.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }
}
