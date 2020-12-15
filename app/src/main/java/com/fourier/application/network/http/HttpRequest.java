package com.fourier.application.network.http;

import com.fourier.application.network.Request;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRequest extends Request {

    protected HttpURLConnection connection;

    public HttpRequest() {
        super();
    }

    public void openConnection(String url) throws IOException {
        this.url = url;
        connection = (HttpURLConnection) new URL(url).openConnection();
    }

    public void addHeader(String name, String value) {
        connection.addRequestProperty(name, value);
    }

    public void setRequestMethod(String name) throws ProtocolException {
        connection.setRequestMethod(name);
    }

    public String getHeader(String name) {
        return connection.getHeaderField(name);
    }

    public void setDoInput(boolean value) {
        connection.setDoInput(value);
    }

    public void setDoOutput(boolean value) {
        connection.setDoOutput(value);
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

    public void setConnection(HttpURLConnection connection) {
        this.connection = connection;
    }

}