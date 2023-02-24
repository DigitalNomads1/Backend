package com.dosi.repositories;

import com.dosi.entities.CgRefCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CgRefCodeRepository extends JpaRepository<CgRefCode, Long> {
     List<CgRefCode> findByRvDomain(String rvDomain);
}