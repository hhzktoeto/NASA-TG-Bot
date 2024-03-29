package hhz.telegram.bots.nasabot.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import hhz.telegram.bots.nasabot.dto.NasaAnswerDto;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NasaClient {

    private NasaClient() {
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String getResponseUrl(String requestUrl) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse response = client.execute(new HttpGet(requestUrl));
            NasaAnswerDto answer = objectMapper.readValue(response.getEntity().getContent(), NasaAnswerDto.class);
            return answer.getUrl();
        } catch (IOException e) {
            return "Сервер NASA не отвечает";
        }
    }
}
