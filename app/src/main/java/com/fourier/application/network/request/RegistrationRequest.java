package com.fourier.application.network.request;

import com.fourier.application.network.https.HttpsRawRequest;
import com.fourier.application.network.https.HttpsRequestBuilder;

import java.io.IOException;

public class RegistrationRequest implements Runnable {

    private String url;
    private String email;
    private String username;
    private String password;
    private HttpsRawRequest request;

    public RegistrationRequest(String url, String email, String username, String password) {
        this.url = url;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        try {
            sendSignUpRequest(url, email, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendSignUpRequest(String url, String email, String username, String password)
            throws IOException {
        request = new HttpsRequestBuilder()
                .openConnection(url)
                .setRequestMethod("POST")
                .setDoInput(true)
                .setDoOutput(true)
                .raw()
                .addItem("email", email)
                .addItem("username", username)
                .addItem("password", password)
                .send();
    }

    public HttpsRawRequest getRequest() {
        return request;
    }

}
