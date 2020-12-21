package com.fourier.application.network.https;

import java.io.IOException;

public class HttpsRawRequestBuilder {

    private HttpsRawRequest rawRequest;

    public HttpsRawRequestBuilder(HttpsRequest request) throws IOException {
        this.rawRequest = new HttpsRawRequest(request);
        rawRequest.startRequest();
    }

    public HttpsRawRequestBuilder addItem(String name, String value) {
        rawRequest.addItem(name, value);
        return this;
    }

    public HttpsRawRequest send() throws IOException {
        rawRequest.endRequest();
        return rawRequest;
    }

}
