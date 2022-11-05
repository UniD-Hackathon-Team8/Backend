package com.meltingzone.meltingzone.dto.template;

import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.item.Character;
import com.meltingzone.meltingzone.domain.item.Consonant;
import com.meltingzone.meltingzone.domain.item.Item;
import com.meltingzone.meltingzone.domain.item.Music;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TemplateDetailResponseDto {
    private Long templateId;
    private String templateName;
    private List<ItemResponseDto> items = new ArrayList<>();

    public TemplateDetailResponseDto(Template template) {
        this.templateId = template.getId();
        this.templateName = template.getTemplateName();

        for (Item item : template.getItemList()) {
            if (item instanceof Character) {
                this.items.add(new ItemResponseDto(
                        "CHARACTER",
                        item.getAnswer(),
                        ((Character) item).getCharacterUrl(),
                        null,
                        null
                ));
            } else if (item instanceof Music) {
                this.items.add(new ItemResponseDto(
                        "MUSIC",
                        item.getAnswer(),
                        null,
                        null,
                        ((Music) item).getMusicUrl()
                ));
            } else if (item instanceof Consonant) {
                this.items.add(new ItemResponseDto(
                        "CONSONANT",
                        item.getAnswer(),
                        null,
                        ((Consonant) item).getConsonantQuestion(),
                        null
                ));
            }
        }
    }
}
