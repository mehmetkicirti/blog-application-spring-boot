package com.mehmetkicirti.blogapplication.utility.security;

import com.mehmetkicirti.blogapplication.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(hasAuthorizationHeader(request)){
            String accessToken = getAccessToken(request);
            if(!tokenProvider.isValidToken(accessToken)){
                filterChain.doFilter(request, response);
                return;
            }
            UserDetails userDetails = getUserDetails(accessToken);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //set spring security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails getUserDetails(String accessToken){
        // get email from token
        String email = tokenProvider.getSubject(accessToken);
        // load user associated with token
        return userDetailService.loadUserByUsername(email);
    }

    private boolean hasAuthorizationHeader(HttpServletRequest request){
        String header = getAuthorizationHeader(request);
        return StringUtils.hasText(header) && header.startsWith("Bearer");
    }

    private String getAccessToken(HttpServletRequest request){
        String header = getAuthorizationHeader(request);
        return header.split("Bearer ")[1].trim();
    }

    private String getAuthorizationHeader(HttpServletRequest request){
        return request.getHeader("Authorization");
    }
}
