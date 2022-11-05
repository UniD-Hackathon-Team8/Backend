package com.meltingzone.meltingzone.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MUSIC")
@Getter
@Setter
public class Music extends Item {

    @Column
    private String musicUrl;
}
