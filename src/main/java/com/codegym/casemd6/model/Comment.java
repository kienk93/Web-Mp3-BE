package com.codegym.casemd6.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import javax.persistence.*;


@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    private Account account;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "song_id")
    private Song song;
}

