package github.mmusica.webshop.service.impl;

import github.mmusica.webshop.service.PostRequestSender;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostRequestSenderImpl implements PostRequestSender {

    private final RestTemplate restTemplate;

    public PostRequestSenderImpl(){
        restTemplate = new RestTemplate();
    }

    @Override
    public String sendPostRequest(String url, String jsonRequestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonRequestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        return responseEntity.getBody();
    }
}
