package dev.danvega.multimodal;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.Media;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class ImageModal {

    private final ChatClient chatClient;

    public ImageModal(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/image-describe")
    public String describeImage() throws IOException {
        byte[] imageData = new ClassPathResource("/images/sincerely-media-2UlZpdNzn2w-unsplash.jpg").getContentAsByteArray();
        UserMessage userMessage = new UserMessage("Can you please explain what you see in the following image?",
                List.of(new Media(MimeTypeUtils.IMAGE_JPEG,imageData)));
        var response = chatClient
                .call(new Prompt(userMessage));
        return response.getResult().getOutput().getContent();
    }

    @GetMapping("/code-describe")
    public String code() throws IOException {
        byte[] imageData = new ClassPathResource("/images/java-open-ai.png").getContentAsByteArray();
        UserMessage userMessage = new UserMessage("The following is a screenshot of some code. Can you do your best to provide a description of what this code does?",
                List.of(new Media(MimeTypeUtils.IMAGE_PNG,imageData)));
        var response = chatClient
                .call(new Prompt(userMessage));
        return response.getResult().getOutput().getContent();
    }

    @GetMapping("/image-to-code")
    public String imageToCode() throws IOException {
        byte[] imageData = new ClassPathResource("/images/java-open-ai.png").getContentAsByteArray();
        UserMessage userMessage = new UserMessage("The following is a screenshot of some code. Can you translate this from the image into text?",
                List.of(new Media(MimeTypeUtils.IMAGE_PNG,imageData)));
        var response = chatClient
                .call(new Prompt(userMessage));
        return response.getResult().getOutput().getContent();
    }

}
