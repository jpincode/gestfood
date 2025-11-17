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
        try {
            boardService.save(boardDTO);
            return ResponseEntity.ok().body("Board created sucessfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> updateBoard(@RequestBody BoardDTO boardDTO) {
        try {
            boardService.update(boardDTO);
            return ResponseEntity.ok().body("Board updated sucessfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBoard(@RequestBody BoardDTO boardDTO) {
        try {
            boardService.delete(boardDTO);
            return ResponseEntity.ok().body("Board deleted sucessfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllBoards() {
        try {
            List<BoardDTO> boardDtos = boardService.findAll();
            return ResponseEntity.ok().body(boardDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoardById(@PathVariable Long id) {
        try {
            BoardDTO boardDTO = boardService.findById(id);
            return ResponseEntity.ok().body(boardDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }        
    }
}
