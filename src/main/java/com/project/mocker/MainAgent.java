package com.project.mocker;

import com.project.mocker.agent.DatabaseAgent;
import com.project.mocker.agent.HttpAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

public class MainAgent {
    public static Logger logger = LoggerFactory.getLogger(MockerApplication.class);
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        String htMode = System.getenv("HT_MODE");
        if ("REPLAY".equalsIgnoreCase(htMode)) {
            logger.info("Running in REPLAY mode");
            HttpAgent.build(instrumentation);
            DatabaseAgent.build(instrumentation);
        }
        else if("RECORD".equalsIgnoreCase(htMode)){
            logger.info("Running in RECORD mode");
            // Do nothing
        }
        else {
            logger.info(htMode);
            throw new IllegalArgumentException("Unsupported mode. Please set correct HT_MODE (RECORD/REPLAY)");
        }
    }
}
