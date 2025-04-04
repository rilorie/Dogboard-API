package com.dogboard.dogboard.Controllers;

import com.dogboard.dogboard.DTO.BoardDTO;
import com.dogboard.dogboard.DTO.BoardEntryDTO;
import com.dogboard.dogboard.Service.BoardEntryService;
import com.dogboard.dogboard.Service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/board-entry")
@CrossOrigin(origins = "http://localhost:3000")
public class BoardEntryController {
    @Autowired
    private BoardEntryService boardEntryService;

    @PostMapping
    public ResponseEntity<BoardEntryDTO> createBoardEntry(
            @RequestBody BoardEntryDTO boardEntryDTO
    ) {
        BoardEntryDTO createdEntry = boardEntryService.createEntry(boardEntryDTO);
        return ResponseEntity.ok(createdEntry);
    }

    @GetMapping
    public List<BoardEntryDTO> getEntriesByBoardIdandUserId(@RequestParam Long boardId, @RequestParam Long userId) {
        return boardEntryService.getEntriesByBoardIdAndUserId(boardId, userId);
    }
}
