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

import com.gestfood.gestfood.business.dtos.BoardDTO;
import com.gestfood.gestfood.business.services.BoardService;


@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<?> createBoard(@RequestBody BoardDTO boardDTO) {
        boolean result = boardService.save(boardDTO);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating board.");
        }

        return ResponseEntity.ok().body("Board created sucessfully!");
    }

    @PutMapping
    public ResponseEntity<?> updateBoard(@RequestBody BoardDTO boardDTO) {
        boolean result = boardService.update(boardDTO);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating board.");
        }

        return ResponseEntity.ok().body("Board updated sucessfully!");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBoard(@RequestBody BoardDTO boardDTO) {
        boolean result = boardService.delete(boardDTO);
        if (!result) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting board.");
        }

        return ResponseEntity.ok().body("Board deleted sucessfully!");
    }

    @GetMapping
    public ResponseEntity<?> getAllBoards() {
        List<BoardDTO> boards = boardService.findAll();
        if (boards == null || boards.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No boards found.");
        }
        
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardById(@PathVariable Long id) {
        BoardDTO boardDTO = boardService.findById(id);
        if (boardDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found with id: " + id);
        }
        
        return ResponseEntity.ok(boardDTO);
    }
}
