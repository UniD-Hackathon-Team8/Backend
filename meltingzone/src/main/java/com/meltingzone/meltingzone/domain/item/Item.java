package com.meltingzone.meltingzone.domain.item;

import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.TimeStamped;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    protected Template template;

    @Column(length = 30)
    protected String answer;

    @Column
    protected boolean isWatched;
}
