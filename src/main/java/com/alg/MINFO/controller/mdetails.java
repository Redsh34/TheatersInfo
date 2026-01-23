package com.alg.MINFO.controller;

import com.alg.MINFO.dto.MovieDTO;
import com.alg.MINFO.service.mservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class mdetails {

    @Autowired
    private mservice ms;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MovieDTO dto){
        String res= ms.saveMovie(dto);
        if(res.equals("MOVIE DETAILS SAVED SUCCESSFULLY")){
            return ResponseEntity.ok("MOVIE DETAILS SAVED SUCCESSFULLY");
        }
        return ResponseEntity.badRequest().body("Invalid data");
    }
}
