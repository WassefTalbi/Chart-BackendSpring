package com.example.ChartTest.Security;


import com.example.ChartTest.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
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


@Slf4j
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
   // @Value("${auth.header}")
    private String TOKEN_HEADER="Authorization";

    @Autowired
    private UserService userService;
    @Autowired
    private TokenUtil tokenUtil;





    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

         final String header = request.getHeader("Authorization");
            final SecurityContext securityContext = SecurityContextHolder.getContext();

            if(header != null && securityContext.getAuthentication() == null){
                String token = header.substring(7);
       
                String username = tokenUtil.getUserNameFromToken(token);
                if(username != null) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    if (tokenUtil.isTokenValid(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }


            filterChain.doFilter(request, response);
        }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return PublicURI.getPublicUri().matches(request);
    }

}












