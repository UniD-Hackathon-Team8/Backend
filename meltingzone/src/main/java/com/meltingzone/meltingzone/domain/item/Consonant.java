package com.meltingzone.meltingzone.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CONSONANT")
@Getter
@Setter
public class Consonant extends Item {

    @Column
    private String consonantQuestion;
}
