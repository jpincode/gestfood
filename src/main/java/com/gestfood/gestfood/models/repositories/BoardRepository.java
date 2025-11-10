package com.gestfood.gestfood.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestfood.gestfood.models.entities.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    
}
