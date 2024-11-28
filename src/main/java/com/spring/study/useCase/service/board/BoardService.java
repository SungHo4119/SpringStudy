package com.spring.study.useCase.service.board;

import com.spring.study.domain.Board;
import com.spring.study.domain.Users;
import com.spring.study.exception.custom.ResourceNotFoundException;
import com.spring.study.exception.message.BoardErrorMessage;
import com.spring.study.infrastructure.repository.BoardRepository;
import com.spring.study.infrastructure.repository.UserRepository;
import com.spring.study.useCase.service.board.dto.ICreateBoardRequestDTO;
import com.spring.study.useCase.service.board.dto.IUpdateBoardRequestDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Board createBoard(ICreateBoardRequestDTO createBoardRequestDTO) {
        Optional<Users> user = userRepository.findById(createBoardRequestDTO.getUserId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException(BoardErrorMessage.USER_NOT_FOUND);
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

    // 게시판 조회
    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isEmpty()) {
            throw new ResourceNotFoundException(BoardErrorMessage.BOARD_NOT_FOUND);
        }
        return board.get();
    }

    // 게시판 수정
    @Transactional
    public Board updateBoard(Long id, IUpdateBoardRequestDTO updateBoardRequestDTO) {
        // 게시판 검색
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BoardErrorMessage.BOARD_NOT_FOUND));
        // 유저 검색
        Users user = userRepository.findById(updateBoardRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(BoardErrorMessage.USER_NOT_FOUND));
        // 비밀번호 검증
        if (!user.getPassword().equals(updateBoardRequestDTO.getPassword())) {
            throw new ResourceNotFoundException(BoardErrorMessage.PASSWORD_NOT_MATCH);
        }

        board.setTitle(updateBoardRequestDTO.getTitle());
        board.setContent(updateBoardRequestDTO.getContent());
        // 저장
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        // 게시판 검색
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(BoardErrorMessage.BOARD_NOT_FOUND));
        // 삭제
        boardRepository.deleteById(id);
    }
}
