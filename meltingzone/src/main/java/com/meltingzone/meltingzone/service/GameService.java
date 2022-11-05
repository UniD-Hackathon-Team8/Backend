package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.domain.Game;
import com.meltingzone.meltingzone.dto.game.GameRequestDto;
import com.meltingzone.meltingzone.dto.game.GameResponseDto;
import com.meltingzone.meltingzone.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;

    public GameResponseDto createGame(GameRequestDto requestDto) {
        Game game = new Game(requestDto);
        gameRepository.save(game);

        return new GameResponseDto(game);
    }

    public List<GameResponseDto> readAllOwnGame() {
        List<GameResponseDto> response = new ArrayList<>();
        gameRepository.findAllByIsCustom(false).stream().forEach(game -> response.add(new GameResponseDto(game)));

        return response;
    }

    public List<GameResponseDto> readAllCustomGame() {
        List<GameResponseDto> response = new ArrayList<>();
        gameRepository.findAllByIsCustom(true).stream().forEach(game -> response.add(new GameResponseDto(game)));

        return response;
    }
}
