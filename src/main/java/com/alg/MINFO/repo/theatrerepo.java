package com.alg.MINFO.repo;

import com.alg.MINFO.entity.theatreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface theatrerepo extends JpaRepository<theatreEntity,Long> {
    boolean existsByTheatreName(String theatreName);
    @Query("SELECT t FROM theatreEntity t")
    List<theatreEntity> getTheatreDetails();


}
