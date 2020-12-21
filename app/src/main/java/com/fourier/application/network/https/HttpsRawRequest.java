package com.fourier.application.network.https;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class HttpsRawRequest extends HttpsRequest {

    private String raw;
    private boolean isItemAdded = false;

    public HttpsRawRequest(HttpsRequest request) {
        this.connection = request.getConnection();
        this.charset = request.getCharset();
        this.outputStream = request.getOutputStream();
        this.url = request.getUrl();
        this.response = request.getResponse();
    }

    public void addItem(String name, String value) {
        if (isItemAdded) {
            writer.append(String.format(",\"%s\":\"%s\"", name, value));
        } else {
            isItemAdded = true;
            writer.append(String.format("{\"%s\":\"%s\"", name, value));
        }
    }

    public void startRequest() throws IOException {
        outputStream = connection.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                true);
    }

    public void endRequest() throws IOException {
        response = new ArrayList<>();
        writer.append("}");
        writer.close();

        // checks server's status code first
        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            connection.disconnect();
        } else {
            throw new IOException("Server returned non-OK status: " + status);
        }
    }

}