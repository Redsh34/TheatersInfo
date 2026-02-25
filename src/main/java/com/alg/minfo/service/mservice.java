package com.alg.minfo.service;

import com.alg.minfo.dto.FullMdetails;
import com.alg.minfo.dto.MovieDTO;
import com.alg.minfo.dto.Movieres;

import java.util.List;


public interface mservice {
  public String saveMovie(FullMdetails moviedto);
  public List<MovieDTO> getMovies();
  public Movieres getMovie(String MovieName);
  public String deleteMovie(String MovieName);
//  public String update(String MovieName, Movieres dto);
}
