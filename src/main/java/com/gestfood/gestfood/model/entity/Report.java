package com.gestfood.gestfood.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.gestfood.gestfood.model.enums.ReportType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fileName;
    
    private ReportType contentType;
    private String author;
    
    @Lob
    private byte[] fileData;

    private LocalDateTime createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
}
