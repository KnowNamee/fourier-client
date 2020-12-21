package com.fourier.application.network.request;

import com.fourier.application.network.http.HttpMultipartRequest;
import com.fourier.application.network.https.HttpsMultipartRequest;
import com.fourier.application.network.https.HttpsRequest;
import com.fourier.application.network.https.HttpsRequestBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadRequest implements Runnable {

    private String url;
    private String token;
    private String filepath;
    private HttpsMultipartRequest request;

    public UploadRequest(String url, String token, String filepath) {
        this.url = url;
        this.token = token;
        this.filepath = filepath;
    }

    @Override
    public void run() {
        try {
            send(url, token, filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send(String url, String token, String filepath) throws IOException {
        request = new HttpsRequestBuilder()
                .openConnection(url)
                .setDoInput(true)
                .setDoOutput(true)
                .addHeader("Authorization", token)
                .setRequestMethod("PUT")
                .multipart()
                .withFile("file", new File(filepath))
                .send();
    }

    public List<String> getResponse() {
        if (request == null) {
            return null;
        }
        return request.getResponse();
    }

}
