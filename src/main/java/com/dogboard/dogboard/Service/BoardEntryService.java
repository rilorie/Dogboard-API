package com.dogboard.dogboard.Service;

import com.dogboard.dogboard.DTO.BoardDTO;
import com.dogboard.dogboard.DTO.BoardEntryDTO;
import com.dogboard.dogboard.Models.Board;
import com.dogboard.dogboard.Models.BoardEntry;
import com.dogboard.dogboard.Repository.BoardEntryRepository;
import com.dogboard.dogboard.Repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
@Service
@AllArgsConstructor
public class BoardEntryService {

    @Autowired
    private BoardEntryRepository boardEntryRepository;

    public BoardEntryDTO createEntry(BoardEntryDTO boardEntryDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        BoardEntry entry = modelMapper.map(boardEntryDTO, BoardEntry.class);
        entry.setDateAdded(Instant.now());
        BoardEntry createdEntry = boardEntryRepository.save(entry);
        BoardEntryDTO createdBoardDTO = modelMapper.map(createdEntry, BoardEntryDTO.class);;
        return createdBoardDTO;
    }

//    public Optional<BoardDTO> getBoardById(Long boardId) {
//        Optional<Board> board = boardRepository.findById(boardId);
//        ModelMapper modelMapper = new ModelMapper();
//        BoardDTO createdBoardDTO = modelMapper.map(board, BoardDTO.class);
//        return Optional.of(createdBoardDTO);
//    }
//
    public List<BoardEntryDTO> getEntriesByBoardIdAndUserId(Long boardId, Long userId) {
        List<BoardEntry> entryList = boardEntryRepository.findAllByBoardIdAndUserId(boardId, userId);
        ModelMapper modelMapper = new ModelMapper();

        return entryList.stream().map(entry -> modelMapper.map(entry, BoardEntryDTO.class)).toList();
    }

}
