package com.dogboard.dogboard.Repository;

import com.dogboard.dogboard.Models.BoardEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardEntryRepository extends JpaRepository<BoardEntry, Long> {
    List<BoardEntry> findAllByBoardIdAndUserId(Long boardId, Long userId);
}
