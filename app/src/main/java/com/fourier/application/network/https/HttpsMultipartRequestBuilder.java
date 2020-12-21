package com.fourier.application.network.https;

import java.io.File;
import java.io.IOException;

public class HttpsMultipartRequestBuilder {

    private HttpsMultipartRequest multipartRequest;

    public HttpsMultipartRequestBuilder(HttpsRequest request, String boundary) throws IOException {
        multipartRequest = new HttpsMultipartRequest(request);
        multipartRequest.setBoundary(boundary);
        multipartRequest.startRequest();
    }

    public HttpsMultipartRequestBuilder withFile(String fieldName, File uploadFile) throws IOException {
        multipartRequest.addFilePart(fieldName, uploadFile);
        return this;
    }

    public HttpsMultipartRequestBuilder withText(String name, String text) {
        multipartRequest.addFormPart(name, text);
        return this;
    }

    public HttpsMultipartRequest send() throws IOException {
        multipartRequest.endRequest();
        return multipartRequest;
    }

}