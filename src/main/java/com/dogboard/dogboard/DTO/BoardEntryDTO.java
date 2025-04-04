package com.dogboard.dogboard.DTO;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class BoardEntryDTO {
    private Long boardEntryId;
    private String photoName;
    private Long boardId;
    private Long userId;
    private Instant dateAdded;
}
