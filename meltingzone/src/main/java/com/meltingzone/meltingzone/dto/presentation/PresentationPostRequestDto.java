package com.meltingzone.meltingzone.dto.presentation;

import lombok.Data;

import java.util.List;

@Data
public class PresentationPostRequestDto {
    private int teamCount;
    private List<TemplateGetRequestDto> templates;
}

@Data
class TemplateGetRequestDto {
    private Long templateId;
    private String templateName;
}
