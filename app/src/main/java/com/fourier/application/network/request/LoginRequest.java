package com.fourier.application.network.request;

import com.fourier.application.network.https.HttpsRawRequest;
import com.fourier.application.network.https.HttpsRequestBuilder;

import java.io.IOException;
import java.util.List;

public class LoginRequest implements Runnable {

    private String url;
    private String username;
    private String password;
    private HttpsRawRequest request;

    public LoginRequest(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        try {
            send(url, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String url, String username, String password)
            throws IOException {
        request = new HttpsRequestBuilder()
                .openConnection(url)
                .setRequestMethod("POST")
                .setDoInput(true)
                .setDoOutput(true)
                .raw()
                .addItem("username", username)
                .addItem("password", password)
                .send();
    }

    public List<String> getResponse() {
        if (request == null) {
            return null;
        }
        return request.getResponse();
    }

    public String getAuthorizationToken() {
        if (request == null) {
            return null;
        }
        return request.getHeader("Authorization");
    }

}