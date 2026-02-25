package com.alg.minfo.repo;

import com.alg.minfo.entity.theatreEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface theatrerepo extends JpaRepository<theatreEntity,Long> {
    boolean existsByTheatreName(String theatreName);
    @Query("SELECT t FROM theatreEntity t")
    List<theatreEntity> getTheatreDetails();

    @Query("SELECT t FROM theatreEntity t WHERE t.theatreName = :theatreName")
    Optional<theatreEntity> getTheatreDetailsByName(String theatreName);
    @Modifying
    @Transactional
    @Query("DELETE FROM theatreEntity t where t.theatreName = :theatreName")
    int deleteTheatreByName(String theatreName);
    @Query("SELECT t FROM MovieEntity m JOIN m.theatres t WHERE m.movieName =:movieName")
    List<theatreEntity> findTheatreByName(String movieName);
}
