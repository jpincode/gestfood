package com.gestfood.gestfood.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestfood.gestfood.business.dto.desk.DeskReponseDTO;
import com.gestfood.gestfood.business.dto.desk.DeskRequestDTO;
import com.gestfood.gestfood.business.dto.desk.DeskUpdateDTO;
import com.gestfood.gestfood.business.service.DeskService;

@RestController
@RequestMapping("/api/desks")
public class DeskController {
    @Autowired
    private DeskService deskService;

    @PostMapping
    public ResponseEntity<String> createDesk(@RequestBody DeskRequestDTO dto) {
        deskService.create(dto);
        return ResponseEntity.ok().body("Mesa cadastrada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDesk(@PathVariable Long id, @RequestBody DeskUpdateDTO dto) {
        deskService.update(id, dto);
        return ResponseEntity.ok().body("Mesa atualizada com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDesk(@PathVariable Long id) {
        deskService.delete(id);
        return ResponseEntity.ok().body("Mesa deletada com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<DeskReponseDTO>> getAllDesks() {
        return ResponseEntity.ok().body(deskService.read());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeskReponseDTO> getDeskById(@PathVariable Long id) {
        return ResponseEntity.ok().body(deskService.read(id));
    }
}
