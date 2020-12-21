package com.fourier.application.network.http;

import java.io.IOException;

public class HttpRequestBuilder {

    private HttpRequest httpRequest;

    public HttpRequestBuilder() {
        httpRequest = new HttpRequest();
    }

    public HttpRequestBuilder(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpRequestBuilder setUrl(String url) {
        httpRequest.setUrl(url);
        return this;
    }

    public HttpRequestBuilder setDoInput(boolean value) {
        httpRequest.setDoInput(value);
        return this;
    }

    public HttpRequestBuilder setDoOutput(boolean value) {
        httpRequest.setDoOutput(value);
        return this;
    }

    public HttpRequestBuilder openConnection(String url) throws IOException {
        httpRequest.openConnection(url);
        return this;
    }

    public HttpRequestBuilder setRequestMethod(String name) throws  IOException {
        httpRequest.setRequestMethod(name);
        return this;
    }

    public HttpRequestBuilder addHeader(String name, String value) {
        httpRequest.addHeader(name, value);
        return this;
    }

    public HttpMultipartRequestBuilder multipart() throws IOException {
        String boundary = "===" + System.currentTimeMillis() + "===";
        addHeader("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        return new HttpMultipartRequestBuilder(httpRequest, boundary);
    }

    public HttpRawRequestBuilder raw() throws IOException {
        return new HttpRawRequestBuilder(httpRequest);
    }

}