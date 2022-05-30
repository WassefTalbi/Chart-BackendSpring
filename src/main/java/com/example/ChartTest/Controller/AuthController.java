package com.example.ChartTest.Controller;


import com.example.ChartTest.DTO.SignInRequest;
import com.example.ChartTest.DTO.JwtResponse;
import com.example.ChartTest.Security.TokenUtil;
import com.example.ChartTest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class AuthController {

    @Autowired
    private TokenUtil tokenUtil;

@Autowired
private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    @CrossOrigin("*")
    public JwtResponse signIn(@RequestBody SignInRequest signInRequest) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(signInRequest.getUsername());
        String token = tokenUtil.generateToken(userDetails);
        System.out.println("this isAuthenticated "+userDetails.getUsername()+"  "+userDetails.getAuthorities()+"  " +userDetails+" App use conncted "+userDetails.getAuthorities());
        JwtResponse response = new JwtResponse(token);
        return response;
    }

}
