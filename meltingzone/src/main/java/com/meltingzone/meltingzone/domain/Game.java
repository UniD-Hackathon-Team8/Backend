package com.meltingzone.meltingzone.domain;

import com.meltingzone.meltingzone.dto.game.GameRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Game extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @Column
    private String description;

    @Column
    private boolean isCustom;

    @Column
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private List<Template> templateList = new ArrayList<>();

    public Game(GameRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.imageUrl = requestDto.getImageUrl();
        this.isCustom = requestDto.getIsCustom();
    }
}
