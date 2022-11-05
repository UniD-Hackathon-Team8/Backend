package com.meltingzone.meltingzone.dto.presentation;

import lombok.Data;

import java.util.List;

@Data
public class PresentationPostResponseDto {
    private Long presentationId;
    private List<TeamPostResponseDto> teams;
    private List<TemplatePostResponseDto> templates;
}

@Data
class TeamPostResponseDto {
    private Long teamId;
    private String teamName;
}

@Data
class TemplatePostResponseDto {
    private Long templateId;
    private String templateName;
    private List<ItemPostResponseDto> items;
}

@Data
class ItemPostResponseDto {
    private Long itemId;
}
