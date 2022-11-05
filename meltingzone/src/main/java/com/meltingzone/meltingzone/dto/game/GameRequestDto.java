package com.meltingzone.meltingzone.dto.game;

import lombok.Data;

@Data
public class GameRequestDto {
    private String name;
    private String description;
    private String imageUrl;
    private Boolean isCustom;
}
