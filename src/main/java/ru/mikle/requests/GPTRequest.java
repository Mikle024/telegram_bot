package ru.mikle.requests;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GPTRequest {
    public static String request(Update update) {
        String apiKey = "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";
        String prompt = update.getMessage().getText();

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.openai.com/v1/engines/gpt-4-turbo-preview/completions");

            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + apiKey);

            String requestBody = "{\"prompt\":\"" + prompt + "\",\"max_tokens\":4000}";

            StringEntity entity = new StringEntity(requestBody);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            String responseBody = EntityUtils.toString(response.getEntity());

            return responseBody;
        } catch (Exception e) {
            return "Error during request: " + e.getMessage();
        }
    }
}
