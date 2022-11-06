package com.meltingzone.meltingzone.dto.presentation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PresentationResumeResponseDto {
    private Long currentItemId;
    private List<TemplatePostResponseDto> templateList;
}
