package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.domain.Game;
import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.item.Character;
import com.meltingzone.meltingzone.domain.item.Consonant;
import com.meltingzone.meltingzone.domain.item.Music;
import com.meltingzone.meltingzone.dto.template.TemplateRequestDto;
import com.meltingzone.meltingzone.repository.GameRepository;
import com.meltingzone.meltingzone.repository.ItemRepository;
import com.meltingzone.meltingzone.repository.TemplateRepository;
import com.meltingzone.meltingzone.util.CustomException;
import com.meltingzone.meltingzone.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;
    private final GameRepository gameRepository;
    private final ItemRepository itemRepository;

    public Long createTemplate(TemplateRequestDto requestDto) {
        Game game = gameRepository.findById(requestDto.getGameId()).orElseThrow(
                () -> new CustomException(ResponseCode.GAME_NOT_FOUND)
        );
        Template template = new Template(requestDto, game);
        templateRepository.save(template);

        requestDto.getItems().forEach( itemDto -> {
            switch(itemDto.getItemType()) {
                case "MUSIC":
                    Music music = new Music(itemDto, template);
                    itemRepository.save(music);
                    template.addItem(music);
                    break;
                case "CHARACTER":
                    Character character = new Character(itemDto, template);
                    itemRepository.save(character);
                    template.addItem(character);
                    break;
                case "CONSONANT":
                    Consonant consonant = new Consonant(itemDto, template);
                    itemRepository.save(consonant);
                    template.addItem(consonant);
                    break;
            }
        });
        templateRepository.save(template);

        return template.getId();
    }

    public Long updateTemplate(Long templateId, TemplateRequestDto requestDto) {
        Template template = templateRepository.findById(templateId).orElseThrow(
                () -> new CustomException(ResponseCode.TEMPLATE_NOT_FOUND)
        );

        template.
    }
}
