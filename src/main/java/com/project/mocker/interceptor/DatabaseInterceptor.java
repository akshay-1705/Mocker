package com.project.mocker.interceptor;

import net.bytebuddy.implementation.bind.annotation.SuperCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class DatabaseInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInterceptor.class);

    public static Long createNewPost(@SuperCall Callable<Long> superCall) throws Exception {
        logger.info("Intercepted create post database call");
        return 1L;
        // return superCall.call();
    }
}
