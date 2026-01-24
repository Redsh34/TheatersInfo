package com.alg.MINFO.controller;

import com.alg.MINFO.dto.FullMdetails;
import com.alg.MINFO.dto.MovieDTO;
import com.alg.MINFO.dto.Movieres;
import com.alg.MINFO.service.mservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class mdetails {

    @Autowired
    private mservice ms;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody FullMdetails dto){
        String res= ms.saveMovie(dto);
        if(res.equals("MOVIE DETAILS SAVED SUCCESSFULLY")){
            return ResponseEntity.ok("MOVIE DETAILS SAVED SUCCESSFULLY");
        }
        return ResponseEntity.badRequest().body("Invalid data");
    }

    @GetMapping("/getMovies")
    public ResponseEntity<List<MovieDTO>> getdetails(){
        List<MovieDTO> dto= ms.getMovies();
        if(dto==null || dto.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getMovie")
    public ResponseEntity<Movieres> getMovieByName(@RequestParam String MovieName){
        Movieres res= ms.getMovie(MovieName);
        if(res==null){
            return ResponseEntity.ok(new Movieres());
        }
        return ResponseEntity.ok(res);
    }
}
