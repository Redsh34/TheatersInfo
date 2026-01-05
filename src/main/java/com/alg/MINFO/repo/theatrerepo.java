package com.alg.MINFO.repo;

import com.alg.MINFO.entity.theatreEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface theatrerepo extends JpaRepository<theatreEntity,Long> {
    boolean existsByTheatreName(String theatreName);
    @Query("SELECT t FROM theatreEntity t")
    List<theatreEntity> getTheatreDetails();

    @Query("SELECT t FROM theatreEntity t WHERE t.theatreName = :theatreName")
    theatreEntity getTheatreDetailsByName(String theatreName);
    @Modifying
    @Transactional
    @Query("DELETE FROM theatreEntity t where t.theatreName = :theatreName")
     int deleteTheatreByName(String theatreName);
}
