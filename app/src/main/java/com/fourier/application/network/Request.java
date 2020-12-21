package com.fourier.application.network;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

public class Request {

    protected static final String LINE_FEED = "\r\n";
    protected String charset = "UTF-8";
    protected String url;
    protected OutputStream outputStream;
    protected PrintWriter writer;
    protected List<String> response;

    public Request() { }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getResponse() {
        return response;
    }

    public void setResponse(List<String> response) {
        this.response = response;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
}
