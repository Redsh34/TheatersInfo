package com.alg.MINFO.service;

import com.alg.MINFO.dto.FullMdetails;
import com.alg.MINFO.dto.MovieDTO;
import com.alg.MINFO.dto.Movieres;
import com.alg.MINFO.dto.theatredto;

import java.util.List;


public interface mservice {
  public String saveMovie(FullMdetails moviedto);
  public List<MovieDTO> getMovies();
  public Movieres getMovie(String MovieName);
  public String deleteMovie(String MovieName);
//  public String update(String MovieName, Movieres dto);
}
