package dev.danvega.multimodal;

import org.springframework.ai.openai.OpenAiAudioTranscriptionClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class AudioModal {

    @Value("classpath:/audio/jfk.flac")
    private Resource audioFile;

    @GetMapping("/transcribe")
    public String transcribe() {
        var openAiAudioApi = new OpenAiAudioApi(System.getenv("OPENAI_API_KEY"));
        var openAiAudioTranscriptionClient = new OpenAiAudioTranscriptionClient(openAiAudioApi);
        var transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .withResponseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
                .withTemperature(0f)
                .build();

        AudioTranscriptionPrompt transcriptionRequest = new AudioTranscriptionPrompt(audioFile, transcriptionOptions);
        AudioTranscriptionResponse response = openAiAudioTranscriptionClient.call(transcriptionRequest);
        return new String(response.getResult().getOutput().getBytes(), StandardCharsets.UTF_8);
    }


}
