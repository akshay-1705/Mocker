package com.project.mocker.agent;

import com.project.mocker.interceptor.DatabaseInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class DatabaseAgent {
    public static Logger logger = LoggerFactory.getLogger(DatabaseAgent.class);

    public static void build(Instrumentation instrumentation) {
        logger.info("Starting database agent");
        // We can directly patch db library
        new AgentBuilder.Default()
                .type(ElementMatchers.named("com.project.mocker.service.PostService"))
                .transform((builder, typeDescription, classLoader, module, classFileLocator) ->
                        builder.method(ElementMatchers.named("createNewPost"))
                                .intercept(MethodDelegation.to(DatabaseInterceptor.class))
                )
                .installOn(instrumentation);
    }
}
