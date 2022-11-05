package com.meltingzone.meltingzone.dto.template;

import com.meltingzone.meltingzone.domain.item.Character;
import com.meltingzone.meltingzone.domain.item.Consonant;
import com.meltingzone.meltingzone.domain.item.Music;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemResponseDto {
    private String itemType;
    private String answer;
    private String character_url = null;
    private String consonant_question = null;
    private String music_url = null;
}
