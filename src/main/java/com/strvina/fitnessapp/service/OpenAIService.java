package com.strvina.fitnessapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {

    public String generatePlan(String prompt) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:11434/api/generate";

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "gemma:2b");
            requestBody.put("prompt", prompt);
            requestBody.put("stream", false);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.getBody());
                return root.path("response").asText().trim();
            }

            return "Ollama nije vratio validan odgovor.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Došlo je do greške pri komunikaciji sa Ollama.";
        }
    }
}
