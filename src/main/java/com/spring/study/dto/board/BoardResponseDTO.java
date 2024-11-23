package com.spring.study.dto.board;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
public class BoardResponseDTO {

  private Long id;
  private String title;
  private String content;
  private Long user_id;
  private LocalDateTime create_at;
  private LocalDateTime update_at;

  @Builder
  public BoardResponseDTO(
      Long id,
      String title,
      String content,
      Long user_id,
      LocalDateTime create_at,
      LocalDateTime update_at
  ) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.user_id = user_id;
    this.create_at = create_at;
    this.update_at = update_at;
  }
}
