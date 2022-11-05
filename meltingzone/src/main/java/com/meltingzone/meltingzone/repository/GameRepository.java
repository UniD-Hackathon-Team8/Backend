package com.meltingzone.meltingzone.repository;

import com.meltingzone.meltingzone.domain.Game;
import com.meltingzone.meltingzone.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByIsCustom(Boolean isCustom);
}
