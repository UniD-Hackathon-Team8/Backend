package com.meltingzone.meltingzone.dto.presentation;

import lombok.Data;

import java.util.List;

@Data
public class TemplatePostResponseDto {
    private Long templateId;
    private String templateName;
    private List<Long> itemIdList;
}
