package com.spring.study.controller.board;

import com.spring.study.domain.Board;
import com.spring.study.dto.board.BoardResponseDTO;
import com.spring.study.dto.board.CreateBoardRequestDTO;
import com.spring.study.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 게시판 생성
    @PostMapping
    public ResponseEntity<BoardResponseDTO> createBoard(
            @Valid @RequestBody CreateBoardRequestDTO createBoardRequestDTO
    ){
        Board board = boardService.createBoard(createBoardRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(board.toBoardResponseDTO());
    }
}
