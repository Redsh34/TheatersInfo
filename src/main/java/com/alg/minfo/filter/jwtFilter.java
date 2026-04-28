
package com.alg.minfo.filter;
import com.alg.minfo.entity.user;
import com.alg.minfo.service.serviceimpl.customservice;
import com.alg.minfo.utility.jwtUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    public customservice customservice;
    @Autowired
    public jwtUtility jwtUtility;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username= jwtUtility.extractUsername(token);
        }
        if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            user user= customservice.loadUserByUsername(username);
            if(jwtUtility.validateToken(token, user , username)){
                UsernamePasswordAuthenticationToken authtoken=  new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request,response);
    }
}

