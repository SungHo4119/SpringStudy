package com.spring.study.service;

import com.spring.study.domain.Board;
import com.spring.study.domain.Users;
import com.spring.study.dto.board.CreateBoardRequestDTO;
import com.spring.study.exception.custom.ResourceNotFoundException;
import com.spring.study.repository.BoardRepository;
import com.spring.study.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    // 게시판 생성
    public Board createBoard(CreateBoardRequestDTO createBoardRequestDTO) {
        Optional<Users> user = userRepository.findById(createBoardRequestDTO.getUserId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        Board board = Board.builder()
                .title(createBoardRequestDTO.getTitle())
                .content(createBoardRequestDTO.getContent())
                .user(user.get())
                .build();
        return boardRepository.save(board);
    }

    // 게시판 목록 조회
    public List<Board> getBoardList() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new ResourceNotFoundException("Board not found");
        }
        return board.get();
    }
}
