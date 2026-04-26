package com.varad.jarvis.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    // IMPORTANT: Paste your actual key here!
    private final String API_KEY = "AIzaSyCKVfgIA5o5PkCzdqFcFeHqMtTJFWWx52k";
    private final String BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    private final WebClient webClient = WebClient.create();

    public String extractTask(String userInput) {
        String fullUrl = BASE_URL + "?key=" + API_KEY;

        String prompt = """
                Extract task details from the following sentence.
                Return ONLY a JSON object. No conversation, no markdown backticks.
                
                Format:
                {
                  "title": "task name",
                  "time": "yyyy-MM-ddTHH:mm:ss"
                }
                
                Input: %s
                """.formatted(userInput);

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        String response = webClient.post()
                .uri(fullUrl)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return cleanJsonResponse(extractText(response));
    }

    private String extractText(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            return root.path("candidates").get(0)
                    .path("content").path("parts").get(0)
                    .path("text").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract text from Gemini");
        }
    }

    private String cleanJsonResponse(String rawAiOutput) {
        // Removes ```json and ``` blocks that Gemini often includes
        return rawAiOutput.replaceAll("```json", "")
                .replaceAll("```", "")
                .trim();
    }
}