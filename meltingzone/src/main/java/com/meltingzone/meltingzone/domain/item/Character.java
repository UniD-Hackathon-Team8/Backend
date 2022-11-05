package com.meltingzone.meltingzone.domain.item;

import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.dto.template.ItemRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHARACTER")
@Getter
@Setter
@NoArgsConstructor
public class Character extends Item {

    @Column
    private String characterUrl;

    public Character(ItemRequestDto requestDto, Template template) {
        this.answer = requestDto.getAnswer();
        this.characterUrl = requestDto.getCharacter_url();
        this.template = template;
    }
}
