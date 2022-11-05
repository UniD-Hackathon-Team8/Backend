package com.meltingzone.meltingzone.domain;


import com.meltingzone.meltingzone.domain.item.Item;
import com.meltingzone.meltingzone.dto.template.TemplateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Template extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(length = 30)
    private String templateName;

    @Column
    private boolean isWatched = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "template")
    private List<Item> itemList = new ArrayList<>();

    public Template(TemplateRequestDto requestDto, Game game) {
        this.templateName = requestDto.getTemplateName();
        this.game = game;
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    public void updateTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public void initItemList() {
        this.itemList = new ArrayList<>();
    }
}
