# Spring AI Multimodal + GPT-4o

OpenAI just launched their newest flagship model GPT-4o, and it comes packed with some exciting features:

- **50% lower.**GPT-4o is 50% cheaper than GPT-4 Turbo, across both input tokens ($5 per 1 million tokens) and output tokens ($15 per 1 million tokens).
- **2x faster latency.**GPT-4o is 2x faster than GPT-4 Turbo.
- **5x higher.**Over the coming weeks, GPT-4o will ramp to 5x those of GPT-4 Turbo—up to 10 million tokens per minute for developers with high usage.

Learn more about [GPT-4o here](https://openai.com/index/hello-gpt-4o/).

## Spring AI

Multimodality refers to a model’s ability to simultaneously understand and process information from various sources, including text, images, audio, and other data formats. Presently, the OpenAI gpt-4-visual-preview and gpt-4o models offers multimodal support. If you want to learn more about multi-modal support
in Spring AI please visit the [documentation](https://docs.spring.io/spring-ai/reference/1.0-SNAPSHOT/api/chat/openai-chat.html#_multimodal).

### Getting Started

To get started you will need register for an [OpenAI account](https://platform.openai.com/apps) and obtain an API Key. Once you have an
api key you can set the following property in `application.properties`. In this example I have extracted the key to an environment variable.You will also
need to set the model to `gpt-4o`.

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o
```

The examples in this repository require at least `1.0.0-SNAPSHOT`. To enable snapshots you will need to add the `spring-snapshots` repository declaration. 

```xml
	<repositories>
		<repository>
			<id>spring-milestones</id>
			<name>Spring Milestones</name>
			<url>https://repo.spring.io/milestone</url>
			<snapshots>
				<enabled>false</enabled>
			</snapshots>
		</repository>
		<repository>
			<id>spring-snapshots</id>
			<name>Spring Snapshots</name>
			<url>https://repo.spring.io/snapshot</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<releases>
				<enabled>false</enabled>
			</releases>
		</repository>
	</repositories>
```

### Chat Modal

The Chat Modal is a simple request handler that will call the chat completions api to generate a dad joke on a given topic. 

```java
@GetMapping("/dad-jokes")
public String jokes(@RequestParam(value = "topic", defaultValue = "Dogs") String topic) {
    PromptTemplate promptTemplate = new PromptTemplate("Tell me a dad joke about {topic}");
    Prompt prompt = promptTemplate.create(Map.of("topic", topic));
    return chatClient.call(prompt).getResult().getOutput().getContent();
}
```

### Image Modal

### Audio Modal