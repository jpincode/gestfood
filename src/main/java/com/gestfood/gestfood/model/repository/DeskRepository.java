package com.gestfood.gestfood.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestfood.gestfood.model.entity.Desk;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {
    
}
