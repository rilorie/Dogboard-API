package com.dogboard.dogboard.Service;

import com.dogboard.dogboard.DTO.BoardDTO;
import com.dogboard.dogboard.Models.Board;
import com.dogboard.dogboard.Models.UserEntity;
import com.dogboard.dogboard.Repository.BoardRepository;
import com.dogboard.dogboard.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
@AllArgsConstructor
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardDTO createBoard(BoardDTO boardDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        Board board = modelMapper.map(boardDTO, Board.class);
        Board createdBoard = boardRepository.save(board);
        BoardDTO createdBoardDTO = modelMapper.map(createdBoard, BoardDTO.class);;
        return createdBoardDTO;
    }

    public Optional<BoardDTO> getBoardById(Long boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        ModelMapper modelMapper = new ModelMapper();
        BoardDTO createdBoardDTO = modelMapper.map(board, BoardDTO.class);
        return Optional.of(createdBoardDTO);
    }

    public List<BoardDTO> getBoardsByUserId(Long boardId) {
        List<Board> boardList = boardRepository.findAllByUserId(boardId);
        ModelMapper modelMapper = new ModelMapper();

        return boardList.stream().map(board -> modelMapper.map(board, BoardDTO.class)).toList();
    }

}
