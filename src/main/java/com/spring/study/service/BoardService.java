package com.spring.study.service;

import com.spring.study.domain.Board;
import com.spring.study.domain.Users;
import com.spring.study.dto.board.CreateBoardRequestDTO;
import com.spring.study.exception.custom.ResourceNotFoundException;
import com.spring.study.repository.BoardRepository;
import com.spring.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public Board createBoard(CreateBoardRequestDTO board) {
        Optional<Users> user = userRepository.findById(board.getUserId());
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        boardRepository.save(board.toEntity());
        return boardRepository.save(board.toEntity());
    }
}
