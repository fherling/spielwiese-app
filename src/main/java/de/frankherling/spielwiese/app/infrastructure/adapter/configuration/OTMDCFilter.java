package de.frankherling.spielwiese.app.infrastructure.adapter.configuration;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Integer.MIN_VALUE)
@Slf4j
public class OTMDCFilter implements Filter {

    public static final String USERID = "userid";
    public static final String ANONYMOUS = "anonymous";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.warn("MDC Filter");
        try {
            MDC.put(USERID, getUserid());
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.clear();
        }
    }

    private String getUserid() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication() != null) {
            return context.getAuthentication().getName();
        }
        return ANONYMOUS;
    }
}
