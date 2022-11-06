package com.meltingzone.meltingzone.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Team extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "presentation_id")
    private Presentation presentation;

    @Column(length = 20)
    private String teamName;

    @Column
    private int score = 0;

    public Team(String teamName, Presentation presentation) {
        this.teamName = teamName;
        this.presentation = presentation;
    }

    public void updateScore(int score) {
        this.score = score;
    }
}
