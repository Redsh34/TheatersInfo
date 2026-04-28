package com.alg.minfo.service.serviceimpl;

import com.alg.minfo.dto.*;
import com.alg.minfo.entity.MovieEntity;
import com.alg.minfo.entity.theatreEntity;
import com.alg.minfo.repo.movierepo;
import com.alg.minfo.repo.theatrerepo;
import com.alg.minfo.service.mservice;
//import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class mservieImpl implements mservice {

    @Autowired
    private movierepo mrepo;

    @Autowired
    private theatrerepo trepo;
    @Override
    @CacheEvict(value = "movies", allEntries = true)
    public String saveMovie(TMDBMovie moviedto) {
       if(moviedto.getTitle()==null || moviedto.getTheatredetails()==null) {
           return "INVALID DATA";
       }

       MovieEntity en= new MovieEntity();
        en.setMovieName(moviedto.getTitle());
        en.setLanguage(moviedto.getOriginal_language());
        if (moviedto.getPoster_path() != null) {
            en.setPosterUrl("https://image.tmdb.org/t/p/w500" + moviedto.getPoster_path());
        }
        LocalDate releaseDate = LocalDate.parse(moviedto.getRelease_date());
        en.setReleaseDate(releaseDate);

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

    @Cacheable("movies")
    public List<MovieDTO> getMovies(){
        System.out.println("DB CALL - getMovies");
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
    @Cacheable(value="movie" ,key ="#MovieName")
    public Movieres getMovie(String MovieName) {
        System.out.println("DB CALL - getMovie");
       MovieEntity en= mrepo.findByMovieName(MovieName);
       if(en==null){
           return null;
       }
       Movieres dto= new Movieres();
       dto.setMovieName(en.getMovieName());
       dto.setPosterUrl(en.getPosterUrl());
       dto.setLanguage(en.getLanguage());
       dto.setReleaseDate(en.getReleaseDate().toString());
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

    @Override
    @CacheEvict(value ={"movies","movie"}, allEntries = true)
    public String deleteMovie(String MovieName) {
        int res= mrepo.deleteMovieByName(MovieName);
        if(res>0){
            return "succesfully deleted the Movie";
        }
        return "NOT FOUND!!";
    }



//    @Override
//    public String update(String MovieName, Movieres dto) {
//       MovieEntity res= mrepo.findByMovieName(MovieName);
//       if(dto.getTheatredetails()!=null){
//
//           res.setTheatres();
//       }
//    }
}
