package com.relive27.openai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ReLive27
 * @date: 2024/1/2 21:42
 */
@Slf4j
@RestController
@RequestMapping("/openai")
@RequiredArgsConstructor
public class CodeController {

    private final OpenAiChatClient chatClient;

    @GetMapping("/top/code/language")
    public String topCodeLanguage() {
        String message = "2023最受欢迎编程语言是什么";
        return chatClient.call(message);
    }

    @GetMapping("/top/code/language/{year}")
    public String topCodeLanguageByYear(@PathVariable("year") Integer year) {
        String message = "{year}最受欢迎编程语言是什么";
        PromptTemplate promptTemplate = new PromptTemplate(message);
        promptTemplate.add("year", year);
        return chatClient.call(promptTemplate.render());
    }

    @GetMapping("/top/code/language/{year}/json")
    public TopLanguage topCodeLanguageByYearForFormat(@PathVariable("year") Integer year) {
        BeanOutputParser<TopLanguage> parser = new BeanOutputParser<>(TopLanguage.class);
        String message = "What is the most popular programming language in {year}? {format}";
        PromptTemplate promptTemplate = new PromptTemplate(message);
        promptTemplate.add("year", year);
        promptTemplate.add("format", parser.getFormat());
        promptTemplate.setOutputParser(parser);

        log.info("FORMAT STRING: " + parser.getFormat());

        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatClient.call(prompt);
        String content = chatResponse.getResult().getOutput().getContent();
        return parser.parse(content);
    }
}
