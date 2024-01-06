package com.relive27.ollama.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ReLive27
 * @date: 2024/1/6 13:57
 */
@Slf4j
@RestController
@RequestMapping("/ollama")
@RequiredArgsConstructor
public class FictionController {

    private final OllamaChatClient chatClient;

    @GetMapping("/top/fiction")
    public String topCodeLanguage() {
        String message = "2023最受欢迎小说是什么";
        return chatClient.generate(message);
    }

    @GetMapping("/top/fiction/{year}")
    public String topCodeLanguageByYear(@PathVariable("year") Integer year) {
        String message = "{year}最受欢迎小说是什么";
        PromptTemplate promptTemplate = new PromptTemplate(message);
        promptTemplate.add("year", year);
        return chatClient.generate(promptTemplate.render());
    }
}
