package com.project.mocker.interceptor;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class CustomCloseableHttpResponse implements CloseableHttpResponse {
    private StatusLine statusLine;
    private Header[] headers;
    private HttpEntity entity;
    private InputStream contentStream;

    public CustomCloseableHttpResponse() {
        // Set status line to 200 OK
        this.statusLine = new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), 200, "OK");

        // Set default headers
        this.headers = new Header[]{
                new BasicHeader("Content-Type", "application/json")
        };

        // Set response body
        String responseBody = "{\"abbreviation\":\"IST\",\"client_ip\":\"49.43.132.60\",\"datetime\":\"2024-03-28T21:02:14.506434+05:30\",\"day_of_week\":4,\"day_of_year\":88,\"dst\":false,\"dst_from\":null,\"dst_offset\":0,\"dst_until\":null,\"raw_offset\":19800,\"timezone\":\"Asia/Kolkata\",\"unixtime\":1711639934,\"utc_datetime\":\"2024-03-28T15:32:14.506434+00:00\",\"utc_offset\":\"+05:30\",\"week_number\":13}";
        this.entity = new StringEntity(responseBody, StandardCharsets.UTF_8);
    }

    @Override
    public void close() throws IOException {
        if (contentStream != null) {
            contentStream.close();
        }
    }

    @Override
    public StatusLine getStatusLine() {
        return statusLine;
    }

    @Override
    public void setStatusLine(StatusLine statusline) {
        this.statusLine = statusline;
    }

    @Override
    public void setStatusLine(ProtocolVersion ver, int code) {
        this.statusLine = new BasicStatusLine(ver, code, "");
    }

    @Override
    public void setStatusLine(ProtocolVersion ver, int code, String reason) {
        this.statusLine = new BasicStatusLine(ver, code, reason);
    }

    @Override
    public void setStatusCode(int code) throws IllegalStateException {
        this.statusLine = new BasicStatusLine(this.statusLine.getProtocolVersion(), code, this.statusLine.getReasonPhrase());
    }

    @Override
    public void setReasonPhrase(String reason) throws IllegalStateException {
        this.statusLine = new BasicStatusLine(this.statusLine.getProtocolVersion(), this.statusLine.getStatusCode(), reason);
    }

    @Override
    public HttpEntity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(HttpEntity httpEntity) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return null;
    }

    @Override
    public boolean containsHeader(String s) {
        return false;
    }

    @Override
    public Header[] getHeaders(String s) {
        return new Header[0];
    }

    @Override
    public Header getFirstHeader(String s) {
        return null;
    }

    @Override
    public Header getLastHeader(String s) {
        return null;
    }

    @Override
    public Header[] getAllHeaders() {
        return new Header[0];
    }

    @Override
    public void addHeader(Header header) {

    }

    @Override
    public void addHeader(String s, String s1) {

    }

    @Override
    public void setHeader(Header header) {

    }

    @Override
    public void setHeader(String s, String s1) {

    }

    @Override
    public void setHeaders(Header[] headers) {

    }

    @Override
    public void removeHeader(Header header) {

    }

    @Override
    public void removeHeaders(String s) {

    }

    @Override
    public HeaderIterator headerIterator() {
        return null;
    }

    @Override
    public HeaderIterator headerIterator(String s) {
        return null;
    }

    @Override
    public HttpParams getParams() {
        return null;
    }

    @Override
    public void setParams(HttpParams httpParams) {

    }

    // Implement other methods as needed
}
