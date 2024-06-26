package com.college.leetcodeclone.filter;

import com.college.leetcodeclone.repository.AccountRepository;
import com.college.leetcodeclone.helper.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.*;
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
    private JwtHelper jwtHelper;

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

        try {
            String username = jwtHelper.extractUsername(token);
                UserDetails user = accountRepository.findByUsername(username).get();
                if (!jwtHelper.validateToken(token, user) || !user.isEnabled()) {
                    log.info("Invalid token");
                    filterChain.doFilter(request, response);
                    return;
                }
                log.info("Token accepted");
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
        } catch (Exception exception) {
            log.info(exception.getMessage());
            filterChain.doFilter(request, response);
        }







    }
}
