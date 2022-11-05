package com.meltingzone.meltingzone.dto.template;

import lombok.Data;

import java.util.List;

@Data
public class TemplateRequestDto {
    private Long gameId;
    private String templateName;
    private List<ItemRequestDto> items;
}
