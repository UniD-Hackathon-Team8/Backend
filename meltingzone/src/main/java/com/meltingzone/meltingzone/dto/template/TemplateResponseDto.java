package com.meltingzone.meltingzone.dto.template;

import com.meltingzone.meltingzone.domain.Template;
import lombok.Data;

@Data
public class TemplateResponseDto {
    private Long templateId;
    private String templateName;

    public TemplateResponseDto(Template template) {
        this.templateId = template.getId();
        this.templateName = template.getTemplateName();
    }
}
