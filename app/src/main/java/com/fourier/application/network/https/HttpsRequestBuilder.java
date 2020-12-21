package com.fourier.application.network.https;

import java.io.IOException;

public class HttpsRequestBuilder {

    private HttpsRequest httpsRequest;

    public HttpsRequestBuilder() {
        httpsRequest = new HttpsRequest();
    }

    public HttpsRequestBuilder(HttpsRequest httpsRequest) {
        this.httpsRequest = httpsRequest;
    }

    public HttpsRequestBuilder setUrl(String url) {
        httpsRequest.setUrl(url);
        return this;
    }

    public HttpsRequestBuilder setDoInput(boolean value) {
        httpsRequest.setDoInput(value);
        return this;
    }

    public HttpsRequestBuilder setDoOutput(boolean value) {
        httpsRequest.setDoOutput(value);
        return this;
    }

    public HttpsRequestBuilder openConnection(String url) throws IOException {
        httpsRequest.openConnection(url);
        return this;
    }

    public HttpsRequestBuilder setRequestMethod(String name) throws  IOException {
        httpsRequest.setRequestMethod(name);
        return this;
    }

    public HttpsRequestBuilder addHeader(String name, String value) {
        httpsRequest.addHeader(name, value);
        return this;
    }

    public HttpsMultipartRequestBuilder multipart() throws IOException {
        String boundary = "===" + System.currentTimeMillis() + "===";
        addHeader("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        return new HttpsMultipartRequestBuilder(httpsRequest, boundary);
    }

    public HttpsRawRequestBuilder raw() throws IOException {
        return new HttpsRawRequestBuilder(httpsRequest);
    }

}