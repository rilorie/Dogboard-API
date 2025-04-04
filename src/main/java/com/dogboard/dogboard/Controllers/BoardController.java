package com.dogboard.dogboard.Controllers;

import com.dogboard.dogboard.DTO.BoardDTO;
import com.dogboard.dogboard.Models.Board;
import com.dogboard.dogboard.Models.UserEntity;
import com.dogboard.dogboard.Service.BoardService;
import com.dogboard.dogboard.Users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(
            @RequestBody BoardDTO boardDTO
    ) {
        BoardDTO createdBoard = boardService.createBoard(boardDTO);
        return ResponseEntity.ok(createdBoard);
    }

    @GetMapping
    public Optional<BoardDTO> getBoard(
            @RequestParam Long boardId
    ) {
        return boardService.getBoardById(boardId);
    }

    @GetMapping
    @RequestMapping("/all-boards-by-user")
    public List<BoardDTO> getBoardsByUserId(
            @RequestParam Long userId
    ) {
        return boardService.getBoardsByUserId(userId);
    }
}
