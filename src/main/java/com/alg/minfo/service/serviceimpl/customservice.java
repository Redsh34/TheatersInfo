package com.alg.minfo.service.serviceimpl;

import com.alg.minfo.entity.user;
import com.alg.minfo.repo.customrepo;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class customservice implements UserDetailsService {

    private final customrepo repo;
    @Override
    public user loadUserByUsername(String username) {
        return repo.findByUsername(username).orElseThrow(()-> new RuntimeException("user not found"));
    }
}
