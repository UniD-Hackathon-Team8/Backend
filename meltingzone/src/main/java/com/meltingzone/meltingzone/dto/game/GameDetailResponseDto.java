package com.meltingzone.meltingzone.dto.game;

import com.meltingzone.meltingzone.dto.template.ItemResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class GameDetailResponseDto {
    private Long gameId;
    private List<ItemResponseDto> items;
}
