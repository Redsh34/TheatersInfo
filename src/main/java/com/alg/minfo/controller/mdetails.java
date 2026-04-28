package com.alg.minfo.controller;

import com.alg.minfo.dto.FullMdetails;
import com.alg.minfo.dto.MovieDTO;
import com.alg.minfo.dto.Movieres;
import com.alg.minfo.dto.TMDBMovie;
import com.alg.minfo.service.mservice;
import com.alg.minfo.service.LoggingService;
import com.alg.minfo.service.serviceimpl.Vision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class mdetails {

    @Autowired
    private mservice ms;

    @Autowired
    private Vision vision;

    @Autowired
    private LoggingService loggingService;
    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody FullMdetails dto){
        TMDBMovie response = vision.searchMovie(dto.getMovieName(), dto.getLanguage());
        if (response == null) {
            return ResponseEntity.badRequest().body("Movie not found in TMDB");
        }
        response.setTheatredetails(dto.getTheatredetails());
        String res= ms.saveMovie(response);
        if(res.equals("MOVIE DETAILS SAVED SUCCESSFULLY")){
            loggingService.logMovieSaved(dto.getMovieName());
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

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMovieByName(@RequestParam String MovieName){
        String res= ms.deleteMovie(MovieName);
        return ResponseEntity.ok(res);
    }

}
