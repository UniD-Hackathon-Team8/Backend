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
@DiscriminatorValue("CONSONANT")
@Getter
@Setter
@NoArgsConstructor
public class Consonant extends Item {

    @Column
    private String consonantQuestion;

    public Consonant(ItemRequestDto requestDto, Template template) {
        this.answer = requestDto.getAnswer();
        this.consonantQuestion = requestDto.getConsonant_question();
        this.template = template;
    }
}
