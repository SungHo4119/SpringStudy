package com.spring.study.infrastructure.controller.board;

import com.spring.study.domain.board.Board;
import com.spring.study.exception.message.PublicErrorMessage;
import com.spring.study.infrastructure.controller.board.dto.BoardPublicDTO;
import com.spring.study.infrastructure.controller.board.dto.CreateBoardRequestDTO;
import com.spring.study.infrastructure.controller.board.dto.UpdateBoardRequestDTO;
import com.spring.study.useCase.service.board.BoardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<BoardPublicDTO> createBoard(
            @Valid @RequestBody CreateBoardRequestDTO createBoardRequestDTO
    ) {
        Board board = boardService.createBoard(createBoardRequestDTO);

        BoardPublicDTO boardResponseDTO = BoardPublicDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .user_id(board.getUser().getId())
                .create_at(board.getCreate_at())
                .update_at(board.getUpdate_at())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(boardResponseDTO);
    }

    // 게시판 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardPublicDTO>> getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        // BoardResponseDTO 리스트로 변환
        List<BoardPublicDTO> boardResponseDTOList = boardList.stream()
                .map(board -> BoardPublicDTO.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .user_id(board.getUser().getId())
                        .create_at(board.getCreate_at())
                        .update_at(board.getUpdate_at())
                        .build()
                )
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTOList);
    }

    // 게시판 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardPublicDTO> getBoard(
            @PathVariable
            @NotNull(message = "ID must not be null") // null 허용 X
            @Positive(message = "ID must be positive") // 양수
            Long id
    ) {
        Board board = boardService.getBoard(id);

        BoardPublicDTO boardResponseDTO = BoardPublicDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .user_id(board.getUser().getId())
                .create_at(board.getCreate_at())
                .update_at(board.getUpdate_at())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTO);
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<BoardPublicDTO> updateBoard(
            @PathVariable
            @NotNull(message = PublicErrorMessage.ID_NOT_NULL)
            @Positive(message = PublicErrorMessage.ID_POSITIVE) // 양수
            Long id,
            @Valid @RequestBody UpdateBoardRequestDTO boardRequestDTO
    ) {
        Board board = boardService.updateBoard(id, boardRequestDTO);

        BoardPublicDTO boardResponseDTO = BoardPublicDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .user_id(board.getUser().getId())
                .create_at(board.getCreate_at())
                .update_at(board.getUpdate_at())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(boardResponseDTO);
    }

    // 게시판 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable
            @NotNull(message = PublicErrorMessage.ID_NOT_NULL)
            @Positive(message = PublicErrorMessage.ID_POSITIVE) // 양수
            Long id
    ) {
        boardService.deleteBoard(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
