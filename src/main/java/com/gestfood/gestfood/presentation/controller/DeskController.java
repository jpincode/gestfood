package com.gestfood.gestfood.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestfood.gestfood.business.dto.DeskDTO;
import com.gestfood.gestfood.business.service.DeskService;


@RestController
@RequestMapping("/api/desks")
public class DeskController {
    @Autowired
    private DeskService deskService;

    @PostMapping
    public ResponseEntity<?> createDesk(@RequestBody DeskDTO deskDTO) {
        try {
            deskService.save(deskDTO);
            return ResponseEntity.ok().body("Desk created sucessfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDesk(@RequestBody DeskDTO deskDTO) {
        try {
            deskService.update(deskDTO);
            return ResponseEntity.ok().body("Desk updated sucessfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDesk(@RequestBody DeskDTO deskDTO) {
        try {
            deskService.delete(deskDTO);
            return ResponseEntity.ok().body("Desk deleted sucessfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllDesks() {
        try {
            List<DeskDTO> deskDtos = deskService.findAll();
            return ResponseEntity.ok().body(deskDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeskById(@PathVariable Long id) {
        try {
            DeskDTO deskDTO = deskService.findById(id);
            return ResponseEntity.ok().body(deskDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }        
    }
}
