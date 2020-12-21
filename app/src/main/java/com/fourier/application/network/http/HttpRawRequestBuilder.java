package com.fourier.application.network.http;

import java.io.IOException;

public class HttpRawRequestBuilder {

    private HttpRawRequest rawRequest;

    public HttpRawRequestBuilder(HttpRequest request) throws IOException {
        this.rawRequest = new HttpRawRequest(request);
        rawRequest.startRequest();
    }

    public HttpRawRequestBuilder addItem(String name, String value) {
        rawRequest.addItem(name, value);
        return this;
    }

    public HttpRawRequest send() throws IOException {
        rawRequest.endRequest();
        return rawRequest;
    }

}
