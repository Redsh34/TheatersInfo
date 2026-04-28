package com.alg.minfo.service;

import com.alg.minfo.dto.FullMdetails;
import com.alg.minfo.dto.MovieDTO;
import com.alg.minfo.dto.Movieres;
import com.alg.minfo.dto.TMDBMovie;

import java.util.List;


public interface mservice {
  public String saveMovie(TMDBMovie moviedto);
  public List<MovieDTO> getMovies();
  public Movieres getMovie(String MovieName);
  public String deleteMovie(String MovieName);

  public default void run(){
    System.out.println("Default method in interface");
  }
  public static void staticMethod(){
    System.out.println("Static method in interface");
  }

//  public String update(String MovieName, Movieres dto);
}
