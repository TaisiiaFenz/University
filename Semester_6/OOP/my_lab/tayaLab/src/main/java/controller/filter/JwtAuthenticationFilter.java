package controller.filter;

import controller.command.auth.JwtManager;
import controller.command.config.SecurityConfig;
import model.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;

public class JwtAuthenticationFilter implements Filter {
    private static final String AUTH_HEADER_KEY = "Authorization";
    private static final String AUTH_HEADER_VALUE_PREFIX = "Bearer";
    private static final int STATUS_CODE_UNAUTHORIZED = 401;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Jwt authentication");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        boolean loggedIn = false;
        try {
            Enumeration<String> headerNames = httpRequest.getHeaderNames();

            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    System.out.println("Header: " + httpRequest.getHeader(headerNames.nextElement()));
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);

//            String jwt = getBearerToken(httpRequest);
//            String url = httpRequest.getRequestURI();
//            url = url.replace("/", "");
//            System.out.println("jwt... " + jwt);
//
//            if (!SecurityConfig.isSecured(url)) {
//                filterChain.doFilter(servletRequest, servletResponse);
//                return;
//            }
//
//            if (url.equals("/login")) {
//                filterChain.doFilter(servletRequest, servletResponse);
//                return;
//            }
//
//
//            JwtManager jwtManager = new JwtManager();
//            if (jwt != null && !jwt.isEmpty() && SecurityConfig.isAccessAllowed(url, Set.of(Role.valueOf(jwtManager.parseToken(jwt).getBody().get("authorities").toString())))) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
//            System.out.println("hello4");
        } catch (final Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setContentLength(0);
            httpResponse.setStatus(STATUS_CODE_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }

    private String getBearerToken(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER_KEY);
        if (authHeader != null && authHeader.startsWith(AUTH_HEADER_VALUE_PREFIX)) {
            return authHeader.substring(AUTH_HEADER_VALUE_PREFIX.length());
        }
        return null;
    }
}
