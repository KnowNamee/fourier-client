package com.fourier.application.network.http;

import java.io.File;
import java.io.IOException;

public class HttpMultipartRequestBuilder {

    private HttpMultipartRequest multipartRequest;

    public HttpMultipartRequestBuilder(HttpRequest request, String boundary) throws IOException {
        multipartRequest = new HttpMultipartRequest(request);
        multipartRequest.setBoundary(boundary);
        multipartRequest.startRequest();
    }

    public HttpMultipartRequestBuilder withFile(String fieldName, File uploadFile) throws IOException {
        multipartRequest.addFilePart(fieldName, uploadFile);
        return this;
    }

    public HttpMultipartRequestBuilder withText(String name, String text) {
        multipartRequest.addFormPart(name, text);
        return this;
    }

    public HttpMultipartRequest send() throws IOException {
        multipartRequest.endRequest();
        return multipartRequest;
    }

}