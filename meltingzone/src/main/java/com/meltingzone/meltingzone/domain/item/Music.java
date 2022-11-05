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
@DiscriminatorValue("MUSIC")
@Getter
@Setter
@NoArgsConstructor
public class Music extends Item {

    @Column
    private String musicUrl;

    public Music(ItemRequestDto requestDto, Template template) {
        this.answer = requestDto.getAnswer();
        this.musicUrl = requestDto.getMusic_url();
        this.template = template;
    }
}
