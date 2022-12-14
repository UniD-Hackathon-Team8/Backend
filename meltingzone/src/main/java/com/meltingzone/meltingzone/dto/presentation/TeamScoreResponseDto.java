package com.meltingzone.meltingzone.dto.presentation;

import lombok.Data;

@Data
public class TeamScoreResponseDto {
    private String teamName;
    private int score;

    public TeamScoreResponseDto(String teamName, int score) {
        this.teamName = teamName;
        this.score = score;
    }
}
