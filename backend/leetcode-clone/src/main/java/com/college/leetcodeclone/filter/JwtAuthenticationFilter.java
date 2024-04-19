package com.college.leetcodeclone.filter;

import com.college.leetcodeclone.repository.AccountRepository;
import com.college.leetcodeclone.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter implements Filter {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.info("filter");
        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwt == null || !jwt.startsWith("Bearer")) {
            log.info("Authentication token is null or incorrect format");
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwt.split("Bearer")[1].trim();

        String username = jwtUtils.extractUsername(token);

        try {
            UserDetails user = accountRepository.findByUsername(username);
            if (!jwtUtils.validateToken(token, user)) {
                log.info("Invalid token");
                filterChain.doFilter(request, response);
                return;
            }
            log.info("Token accepted");
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            log.info("{}",ex.getMessage());
        }







    }
}
