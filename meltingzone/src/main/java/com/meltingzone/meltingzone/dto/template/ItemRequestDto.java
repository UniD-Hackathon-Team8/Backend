package com.meltingzone.meltingzone.dto.template;

import lombok.Data;

@Data
public class ItemRequestDto {
    private String itemType;
    private String answer;
    private String character_url = null;
    private String consonant_question = null;
    private String music_url = null;
    private Long template_id;
}
