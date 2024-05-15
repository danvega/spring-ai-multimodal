package dev.danvega.multimodal;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/chat")
public class ChatModal {

    private final ChatClient chatClient;

    public ChatModal(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/dad-jokes")
    public String jokes(@RequestParam(value = "topic", defaultValue = "Dogs") String topic) {
        PromptTemplate promptTemplate = new PromptTemplate("Tell me a dad joke about {topic}");
        Prompt prompt = promptTemplate.create(Map.of("topic", topic));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }
}
