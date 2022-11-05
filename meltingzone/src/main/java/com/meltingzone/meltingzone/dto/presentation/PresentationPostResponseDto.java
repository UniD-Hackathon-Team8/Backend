package com.meltingzone.meltingzone.dto.presentation;

import lombok.Data;

import java.util.List;

@Data
public class PresentationPostResponseDto {
    private Long presentationId;
    private List<TeamPostResponseDto> teams;
    private List<TemplatePostResponseDto> templates;
}

