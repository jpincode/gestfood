package com.gestfood.gestfood.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestfood.gestfood.business.dtos.BoardDTO;
import com.gestfood.gestfood.models.entities.Board;
import com.gestfood.gestfood.models.repositories.BoardRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private ConverterService converterService;

    public void save(BoardDTO boardDto) {
        try {
            if(boardDto.getSeats() <= 0) {
                throw new IllegalArgumentException("Board must have a positive number of seats.");
            }
            if(boardDto.getSeats() > 15) {
                throw new IllegalArgumentException("The number of seats must be less than 15.");
            }
            Board board = converterService.dtoToBoard(boardDto);
            if (board == null) {
                throw new IllegalArgumentException("Could not convert board DTO to entity");
            }
            boardRepository.save(board);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void update(BoardDTO boardDto) {
        try {
            if(boardDto.getSeats() <= 0) {
                throw new IllegalArgumentException("Board must have a positive number of seats.");
            }
            if(boardDto.getSeats() > 15) {
                throw new IllegalArgumentException("The number of seats must be less than 15.");
            }
            Board board = converterService.dtoToBoard(boardDto);
            if (board == null) {
                throw new IllegalArgumentException("Could not convert board DTO to entity");
            }
            boardRepository.save(board);
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional
    public void delete(BoardDTO boardDto) {
        try {
            Board board = converterService.dtoToBoard(boardDto);
            if (board == null) {
                throw new IllegalArgumentException("Could not convert board DTO to entity");
            }
            boardRepository.delete(board);
        } catch (Exception e) {
            throw e;
        }
    }

    public List<BoardDTO> findAll() {
        try {
            List<Board> boards = boardRepository.findAll();
            List<BoardDTO> boardDTOs = new ArrayList<>();

            if(boards.isEmpty()) {
                throw new RuntimeException("There are no tables registered.");
            }

            for (Board board : boards) {
                boardDTOs.add(converterService.boardToDto(board));
            }

            return boardDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    public BoardDTO findById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Board ID cannot be null");
            }
            Optional<Board> board = boardRepository.findById(id);
            if (board.isEmpty()) {
                throw new RuntimeException("Board not found with id: " + id);
            }
            BoardDTO boardDTO = converterService.boardToDto(board.get());
            return boardDTO;
        } catch (Exception e) {
            throw e;
        }
    }
}
