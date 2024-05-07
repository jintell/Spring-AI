package com.jade.platform.springaiintro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jade.platform.springaiintro.model.*;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @Author: Josiah Adetayo
 * @Email: josleke@gmail.com, josiah.adetayo@meld-tech.com
 * @Date: 5/4/24
 */
@Service
public class OpenAiService {

    private final ChatClient chatClient;
    private final ObjectMapper mapper;

    public OpenAiService(ChatClient chatClient, ObjectMapper mapper) {
        this.chatClient = chatClient;
        this.mapper = mapper;
    }

    @Value("classpath:template/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:template/get-capital-format-prompt.st")
    private Resource getCapitalFormatPrompt;

    @Value("classpath:template/get-president-prompt.st")
    private Resource getPresidentPrompt;

    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatClient.call(prompt);

        return chatResponse.getResult().getOutput().getContent();
    }


    public Answer getAnswer(Question question) {
        PromptTemplate promptTemplate = new PromptTemplate(question.question());
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatClient.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

    public Answer getAnswer(GetCapitalRequest capitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry())); // This is binding
        ChatResponse chatResponse = chatClient.call(prompt);
        String jsonnResponse = "";
        System.out.println(chatResponse.getResult().getOutput().getContent());

        try {
            JsonNode node = mapper.readTree(chatResponse.getResult().getOutput().getContent());
            jsonnResponse = node.get("answer").toPrettyString();
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new Answer(jsonnResponse);
    }
    public Answer getAnswerWithFormat(GetCapitalRequest capitalRequest) {
        BeanOutputParser<GetCapitalResponse> parser = new BeanOutputParser<>(GetCapitalResponse.class);
        String format = parser.getFormat();

        System.out.println(format);

        PromptTemplate promptTemplate = new PromptTemplate(getCapitalFormatPrompt);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry(),
                "format", format)); // This is binding
        ChatResponse chatResponse = chatClient.call(prompt);

        System.out.println(chatResponse.getResult().getOutput().getContent());

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

    public Answer getAnswer(GetPresidentRequest presidentRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getPresidentPrompt);
        Prompt prompt = promptTemplate.create(Map.of("presidentOrCompany", presidentRequest.presidentOrCompany())); // This is binding
        ChatResponse chatResponse = chatClient.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }
}
