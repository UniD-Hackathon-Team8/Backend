package com.meltingzone.meltingzone.domain;

import javax.persistence.*;

public class Team extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Presentation presentation;

    @Column(length = 20)
    private String teamName;

    @Column
    private int score;
}
