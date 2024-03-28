package com.project.mocker.agent;

import com.project.mocker.interceptor.HttpInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class HttpAgent {
    public static Logger logger = LoggerFactory.getLogger(HttpAgent.class);

    public static void build(Instrumentation instrumentation) {
        logger.info("Starting http agent");
        // We can directly patch java.net.http library
        new AgentBuilder.Default()
                .type(ElementMatchers.named("org.apache.http.impl.client.InternalHttpClient"))
                .transform((builder, typeDescription, classLoader, module, classFileLocator) ->
                        builder.method(ElementMatchers.named("doExecute"))
                                .intercept(MethodDelegation.to(HttpInterceptor.class))
                )
                .installOn(instrumentation);
    }
}
