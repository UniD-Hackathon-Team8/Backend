package com.meltingzone.meltingzone.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CHARACTER")
@Getter
@Setter
public class Character extends Item {

    @Column
    private String characterUrl;
}
