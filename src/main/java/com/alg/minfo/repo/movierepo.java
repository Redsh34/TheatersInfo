package com.alg.minfo.repo;

import com.alg.minfo.entity.MovieEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface movierepo extends JpaRepository<MovieEntity,Long> {

    @Query("SELECT t FROM MovieEntity t WHERE t.movieName = :movieName")
   MovieEntity findByMovieName(String movieName);

   @Query("SELECT t FROM MovieEntity t")
   List<MovieEntity> findAll();

    @Modifying
    @Transactional
    @Query("DELETE FROM MovieEntity t WHERE t.movieName = :movieName")
    int deleteMovieByName(String movieName);

}
