package com.fewbytes.jetty.log4j;

import org.eclipse.jetty.http.HttpHeaders;
import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;

import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by avishai on 2/2/14.
 */
public class JettyRequestResponseDescriptor implements Serializable {
    public final String protocol;
    public final String method;
    public final String requestURL;
    public final String requestURI;
    public final int status;
    public final long latency;
    public final String remoteHost;
    public final int remotePort;
    public final String scheme;
    public final String localName;
    public final int localPort;
    public final long responseLength;
    public final String serverName;
    public final long timestamp;
    public final HashMap<String, String> cookies;
    public String user;
    public String referrer;
    public String userAgent;
    public String X_Forwarded_For;

    public JettyRequestResponseDescriptor(Request request, Response response) {
        method = request.getMethod();
        requestURL = request.getRequestURL().toString();
        status = response.getStatus();
        latency = System.currentTimeMillis() - request.getTimeStamp();
        remoteHost = request.getRemoteHost();
        remotePort = request.getRemotePort();
        scheme = request.getScheme();
        protocol = request.getProtocol();
        localName = request.getLocalName();
        localPort = request.getLocalPort();
        requestURI = request.getRequestURI();
        responseLength = response.getContentCount();
        serverName = request.getServerName();
        if (request.getAuthentication() instanceof Authentication.User) {
            user = ((Authentication.User) request.getAuthentication()).getUserIdentity().getUserPrincipal().getName();
        }
        timestamp = request.getTimeStamp();
        cookies = new HashMap<String, String>();
        for (Cookie cookie: request.getCookies()) {
            cookies.put(cookie.getName(), cookie.getValue());
        }
        referrer = request.getHeader(HttpHeaders.REFERER);
        userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        X_Forwarded_For = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
    }
}
