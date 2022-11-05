package com.meltingzone.meltingzone.controller;

import com.meltingzone.meltingzone.dto.template.TemplateRequestDto;
import com.meltingzone.meltingzone.service.TemplateService;
import com.meltingzone.meltingzone.util.ResponseCode;
import com.meltingzone.meltingzone.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/template")
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping
    public ResponseEntity<ResponseMessage> createTemplate(@RequestBody TemplateRequestDto requestDto) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                templateService.createTemplate(requestDto)
        );
    }

    @PatchMapping("/{templateId}")
    public ResponseEntity<ResponseMessage> updateTemplate(@PathVariable Long templateId,  @RequestBody TemplateRequestDto requestDto) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                templateService.updateTemplate(templateId, requestDto)
        );
    }
}
