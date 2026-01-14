package com.alg.MINFO.service.serviceimpl;

import com.alg.MINFO.dto.MovieDTO;
import com.alg.MINFO.entity.MovieEntity;
import com.alg.MINFO.repo.movierepo;
import com.alg.MINFO.service.mservice;

public class mservieImpl implements mservice {

    private movierepo mrepo;
    @Override
    public String saveMovie(MovieDTO moviedto) {
       if(moviedto.getMovieName()!=null){
           MovieEntity en= new MovieEntity();
           en.setMovieName(moviedto.getMovieName());
           en.setPosterUrl(moviedto.getPosterUrl());
           en.setLanguage(moviedto.getLanguage());
          mrepo.save(en);
          return "MOVIE DETAILS SAVED SUCCESSFULLY";
       }
       return "INVALID DATA";
    }
}
