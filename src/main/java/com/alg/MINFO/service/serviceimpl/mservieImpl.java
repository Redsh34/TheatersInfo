package com.alg.MINFO.service.serviceimpl;

import com.alg.MINFO.dto.FullMdetails;
import com.alg.MINFO.dto.MovieDTO;
import com.alg.MINFO.dto.Movieres;
import com.alg.MINFO.dto.theatredto;
import com.alg.MINFO.entity.MovieEntity;
import com.alg.MINFO.entity.theatreEntity;
import com.alg.MINFO.repo.movierepo;
import com.alg.MINFO.repo.theatrerepo;
import com.alg.MINFO.service.mservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class mservieImpl implements mservice {

    @Autowired
    private movierepo mrepo;

    @Autowired
    private theatrerepo trepo;
    @Override
    public String saveMovie(FullMdetails moviedto) {
       if(moviedto.getMovieName()==null || moviedto.getTheatredetails()==null) {
           return "INVALID DATA";
       }
        MovieEntity en= new MovieEntity();
        en.setMovieName(moviedto.getMovieName());
        en.setPosterUrl(moviedto.getPosterUrl());
        en.setLanguage(moviedto.getLanguage());
        en.setPlot(moviedto.getPlot());
        List<theatreEntity> details= new ArrayList<>();
        for(String theatreName : moviedto.getTheatredetails()){
            theatreEntity t= trepo.getTheatreDetailsByName(theatreName).
                    orElseThrow(() -> new RuntimeException("TheatreNotFound" + theatreName));
            details.add(t);
        }
        en.setTheatres(details);
        mrepo.save(en);
        return "MOVIE DETAILS SAVED SUCCESSFULLY";
    }

    public List<MovieDTO> getMovies(){
        List<MovieEntity> en = mrepo.findAll();
        if(en==null){
            return null;
        }
        List<MovieDTO> mdto= new ArrayList<>();
        for(int i=0;i<en.size();i++){
            MovieDTO dto = new MovieDTO();
            MovieEntity e= en.get(i);
            dto.setMovieName(e.getMovieName());
            dto.setLanguage(e.getLanguage());
            dto.setPosterUrl(e.getPosterUrl());
            mdto.add(dto);
        }
        return mdto;
    }

    @Override
    public Movieres getMovie(String MovieName) {
       MovieEntity en= mrepo.findByMovieName(MovieName);
       if(en==null){
           return null;
       }
       Movieres dto= new Movieres();
       dto.setMovieName(en.getMovieName());
       dto.setPosterUrl(en.getPosterUrl());
       dto.setLanguage(en.getLanguage());
       dto.setPlot(en.getPlot());
       List<theatredto> tlist= new ArrayList<>();
       List<theatreEntity> res= en.getTheatres();
       for(int i=0;i<res.size();i++){
           theatreEntity ren = res.get(i);
           theatredto dt= new theatredto();
           dt.setTheatreName(ren.getTheatreName());
           dt.setCity(ren.getCity());
           dt.setMaps(ren.getMaps());
           tlist.add(dt);
       }
       dto.setTheatredetails(tlist);
       return dto;

    }
}
