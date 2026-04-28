package com.alg.minfo.repo;


import com.alg.minfo.entity.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface customrepo extends JpaRepository<user, Long> {
    Optional<user> findByUsername(String username);

}
