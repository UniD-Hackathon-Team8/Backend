package com.meltingzone.meltingzone.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
public class Presentation extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "presentation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 30)
    private String presentationName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presentation")
    private List<Team> teamList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "presentation")
    private List<Template> templateList = new ArrayList<>();
}
