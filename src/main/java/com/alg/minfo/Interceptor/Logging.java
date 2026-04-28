package com.alg.minfo.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor that runs before controller handlers (preHandle) and after completion.
 * - Logs incoming request method, URI and remote address
 * - Optionally enforces an X-API-KEY header when property `app.security.api-key` is set
 * - Measures request processing time and logs it in afterCompletion
 */
@Component
public class Logging implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Logging.class);

    // If this property is set (in your config server or local YAML), the interceptor will validate X-API-KEY
    @Value("${app.security.api-key:}")
    private String requiredApiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long start = System.currentTimeMillis();
        request.setAttribute("startTime", start);

        String method = request.getMethod();
        String uri = request.getRequestURI();
        String qs = request.getQueryString();
        String remote = request.getRemoteAddr();

        logger.info("Incoming request -> {} {}{} from {}", method, uri, (qs == null ? "" : "?" + qs), remote);

        // If an API key is configured, enforce it. If not configured (empty), skip enforcement.
        if (requiredApiKey != null && !requiredApiKey.isBlank()) {
            String apiKey = request.getHeader("X-API-KEY");
            if (apiKey == null || !apiKey.equals(requiredApiKey)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Unauthorized\"}");
                logger.warn("Unauthorized request to {} from {} - missing/invalid API key", uri, remote);
                return false; // Stop the request - controller will not be invoked
            }
        }

        return true; // Proceed to controller
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object o = request.getAttribute("startTime");
        if (o instanceof Long) {
            long duration = System.currentTimeMillis() - (Long) o;
            logger.info("Completed {} {} in {} ms - status={}", request.getMethod(), request.getRequestURI(), duration, response.getStatus());
        }
        if (ex != null) {
            logger.error("Request processing resulted in exception", ex);
        }
    }

}
