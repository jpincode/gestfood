package com.gestfood.gestfood.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestfood.gestfood.model.entity.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
