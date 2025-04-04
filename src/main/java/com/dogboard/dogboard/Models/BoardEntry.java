package com.dogboard.dogboard.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name="board_entry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BoardEntry {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="board_entry_id")
    private Long boardEntryId;
    private String photoName;
    private Long userId;
    private Long boardId;

    @CreatedDate
    private Instant dateAdded;
}
