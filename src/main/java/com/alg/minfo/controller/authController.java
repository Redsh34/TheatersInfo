package com.alg.minfo.controller;

import com.alg.minfo.config.securityconfig;
import com.alg.minfo.dto.AuthRequest;
import com.alg.minfo.utility.jwtUtility;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class authController {
    @Autowired
    public securityconfig securityConfig;
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public jwtUtility jwtobj;

    @PostMapping("/login")
    public String auth(@RequestBody AuthRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            return jwtobj.generateToken(request.getUsername());
        }
        catch (Exception e) {
            return "Authentication failed: " + e.getMessage();
        }
    }
}
