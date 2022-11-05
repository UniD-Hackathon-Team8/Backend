package com.meltingzone.meltingzone.dto.game;

import com.meltingzone.meltingzone.domain.Game;
import lombok.Data;

@Data
public class GameResponseDto {
    private Long gameId;
    private String gameName;
    private String description;
    private String imageUrl;

    public GameResponseDto(Game game) {
        this.gameId = game.getId();
        this.gameName = game.getName();
        this.description = game.getDescription();
        this.imageUrl = game.getImageUrl();
    }
}
