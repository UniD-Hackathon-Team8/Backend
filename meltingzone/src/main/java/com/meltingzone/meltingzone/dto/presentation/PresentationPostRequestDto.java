package com.meltingzone.meltingzone.dto.presentation;

import lombok.Data;

import java.util.List;

@Data
public class PresentationPostRequestDto {
    private String presentationName;
    private int teamCount;
    private List<Long> templateIdList;
}
