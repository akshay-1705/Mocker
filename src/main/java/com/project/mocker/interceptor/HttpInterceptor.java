package com.project.mocker.interceptor;

import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.concurrent.Callable;

public class HttpInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(HttpInterceptor.class);

    public static String get(@SuperCall Callable<String> superCall,
                             @Argument(0) String url) throws Exception {
        logger.info("Intercepted HTTP request to URL: " + url);

        if (Objects.equals(url, "http://worldtimeapi.org/api/timezone/Asia/Kolkata")) {
            return "{\"abbreviation\":\"PST\",\"client_ip\":\"49.43.132.234\",\"datetime\":\"2024-03-21T02:18:23.628331+05:30\",\"day_of_week\":4,\"day_of_year\":81,\"dst\":false,\"dst_from\":null,\"dst_offset\":0,\"dst_until\":null,\"raw_offset\":19800,\"timezone\":\"Asia/Kolkata\",\"unixtime\":1710967703,\"utc_datetime\":\"2024-03-20T20:48:23.628331+00:00\",\"utc_offset\":\"+05:30\",\"week_number\":12}";
        } else {
            return superCall.call();
        }
    }
}
