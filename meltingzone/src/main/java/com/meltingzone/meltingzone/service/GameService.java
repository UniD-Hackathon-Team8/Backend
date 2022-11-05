package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.domain.Game;
import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.item.Character;
import com.meltingzone.meltingzone.domain.item.Consonant;
import com.meltingzone.meltingzone.domain.item.Item;
import com.meltingzone.meltingzone.domain.item.Music;
import com.meltingzone.meltingzone.dto.game.GameDetailResponseDto;
import com.meltingzone.meltingzone.dto.game.GameRequestDto;
import com.meltingzone.meltingzone.dto.game.GameResponseDto;
import com.meltingzone.meltingzone.dto.template.ItemResponseDto;
import com.meltingzone.meltingzone.repository.GameRepository;
import com.meltingzone.meltingzone.repository.TemplateRepository;
import com.meltingzone.meltingzone.util.CustomException;
import com.meltingzone.meltingzone.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {
    private final GameRepository gameRepository;
    private final TemplateRepository templateRepository;

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

    public GameDetailResponseDto readGameDetail(Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new CustomException(ResponseCode.GAME_NOT_FOUND)
        );
        Template gameTemplate = templateRepository.findById(game.getId()).orElseThrow(
                () -> new CustomException(ResponseCode.TEMPLATE_NOT_FOUND)
        );

        GameDetailResponseDto response = new GameDetailResponseDto();
        response.setGameId(game.getId());

        ArrayList<ItemResponseDto> itemDtos = new ArrayList<>();
        for (Item item : gameTemplate.getItemList()) {
            if (item instanceof Character) {
                itemDtos.add(new ItemResponseDto(
                        "CHARACTER",
                        item.getAnswer(),
                        ((Character) item).getCharacterUrl(),
                        null,
                        null
                ));
            }
            else if (item instanceof Music) {
                itemDtos.add(new ItemResponseDto(
                        "MUSIC",
                        item.getAnswer(),
                        null,
                        null,
                        ((Music) item).getMusicUrl()
                ));
            }
            else if (item instanceof Consonant) {
                itemDtos.add(new ItemResponseDto(
                        "CONSONANT",
                        item.getAnswer(),
                        null,
                        ((Consonant) item).getConsonantQuestion(),
                        null
                ));
            }
        }
        response.setItems(itemDtos);

        return response;
    }
}
