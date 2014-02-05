package com.fewbytes.jetty.log4j;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.util.component.AbstractLifeCycle;

/**
 * Created by avishai on 2/2/14.
 */
public class Log4JRequestHandler extends AbstractLifeCycle implements RequestLog {
    Logger logger;
    final String loggerName;
    public Log4JRequestHandler(String loggerName) {
        this.loggerName = loggerName;
    }

    @java.lang.Override
    public void log(Request request, Response response) {
        logger.info(new JettyRequestResponseDescriptor(request, response));
    }

    @java.lang.Override
    public void doStart() throws Exception {
        logger = Logger.getLogger(loggerName);
    }
}
