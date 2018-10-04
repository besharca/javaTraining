package com.lil.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lil.demo.model.PostLogger;

@Repository
public interface LoggRepository extends JpaRepository<PostLogger, Integer>{

}
