package com.peterporzuczek.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilterConfiguration implements Filter {

    @Autowired
    private Environment enviroment;

    @Override
    public void init(FilterConfig fc) throws ServletException { }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        String endpoint = enviroment.getProperty("cors.endpoint");

        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", endpoint);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Origin, Accept, Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With, params");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() { }
}