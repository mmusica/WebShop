package github.mmusica.webshop.service;

public interface PostRequestSender {
    String sendPostRequest(String url, String jsonRequestBody);
}
