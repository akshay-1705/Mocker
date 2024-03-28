package com.project.mocker.interceptor;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;
import java.util.concurrent.Callable;

public class HttpInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);

    public static CloseableHttpResponse doExecute(@SuperCall Callable<CloseableHttpResponse> superCall, @Argument(0) HttpHost target,  @Argument(1) HttpRequest request,  @Argument(2) HttpContext context) throws Exception {
        logger.info("Intercepted HTTP request to URL: " + request.getRequestLine().getUri());

        if (Objects.equals(request.getRequestLine().getUri(), "http://worldtimeapi.org/api/timezone/Asia/Kolkata") &&
                Objects.equals(request.getRequestLine().getMethod(), "GET")) {
            // Make this more custom (Add args: response body, code, status, etc)
            return new CustomCloseableHttpResponse();
        }
        else {
            return superCall.call();
        }
    }
}

