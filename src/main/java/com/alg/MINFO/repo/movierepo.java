package com.alg.MINFO.repo;

import com.alg.MINFO.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface movierepo extends JpaRepository<MovieEntity,Long> {
   Optional<MovieEntity> findByMovieName(String movieName);
}
