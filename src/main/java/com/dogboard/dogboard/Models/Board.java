package com.dogboard.dogboard.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="boards")
@Data
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="board_id")
    private Long boardId;
    private String boardName;
    private Long userId;
}
