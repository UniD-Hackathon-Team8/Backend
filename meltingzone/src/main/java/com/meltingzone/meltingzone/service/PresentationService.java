package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.domain.Presentation;
import com.meltingzone.meltingzone.domain.Team;
import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.User;
import com.meltingzone.meltingzone.domain.item.Character;
import com.meltingzone.meltingzone.domain.item.Consonant;
import com.meltingzone.meltingzone.domain.item.Item;
import com.meltingzone.meltingzone.domain.item.Music;
import com.meltingzone.meltingzone.dto.game.GameResponseDto;
import com.meltingzone.meltingzone.dto.presentation.*;
import com.meltingzone.meltingzone.dto.template.ItemResponseDto;
import com.meltingzone.meltingzone.repository.*;
import com.meltingzone.meltingzone.util.CustomException;
import com.meltingzone.meltingzone.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class PresentationService {
    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final TeamRepository teamRepository;
    private final PresentationRepository presentationRepository;
    private final ItemRepository itemRepository;

    public PresentationPostResponseDto createPresentation(PresentationPostRequestDto requestDto, String email) {
        Presentation presentation = new Presentation();
        presentationRepository.save(presentation);
        List<Template> templateList = requestDto.getTemplateIdList().stream()
                .map(id -> templateRepository.findById(id)
                        .orElseThrow(
                                () -> new CustomException(ResponseCode.TEMPLATE_NOT_FOUND)
                        )
                )
                .collect(Collectors.toList());

        List<Team> teamList = saveTeamReturnTeamList(requestDto.getTeamCount(), presentation);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));

        presentation.setPresentationName(requestDto.getPresentationName());
        presentation.setTemplateList(templateList);
        presentation.setTeamList(teamList);
        presentation.setUser(user);
        presentationRepository.save(presentation);

        PresentationPostResponseDto responseDto = new PresentationPostResponseDto();
        responseDto.setPresentationId(presentation.getId());
        List<TeamPostResponseDto> teamPostResponseDtos = getTeamPostResponseDto(teamList);
        responseDto.setTeams(teamPostResponseDtos);
        List<TemplatePostResponseDto> templatePostResponseDtos = getTemplatePostResponseDto(templateList);
        responseDto.setTemplates(templatePostResponseDtos);

        return responseDto;
    }

    

    private List<Team> saveTeamReturnTeamList(int teamCount, Presentation presentation) {
        List<Team> teamList= new ArrayList<>();
        for (int i = 1; i <= teamCount; i++) {
            Team team = new Team(i + "???", presentation);
            teamRepository.save(team);
            teamList.add(team);
        }
        return teamList;
    }

    private List<TeamPostResponseDto> getTeamPostResponseDto(List<Team> teamList) {
        List<TeamPostResponseDto> results = new ArrayList<>();
        for (Team team : teamList) {
            TeamPostResponseDto responseDto = new TeamPostResponseDto();
            responseDto.setTeamId(team.getId());
            responseDto.setTeamName(team.getTeamName());
            results.add(responseDto);
        }

        return results;
    }

    private List<TemplatePostResponseDto> getTemplatePostResponseDto(List<Template> templateList) {
        List<TemplatePostResponseDto> responseDtos = new ArrayList<>();
        for (Template template : templateList) {
            TemplatePostResponseDto responseDto = new TemplatePostResponseDto();
            responseDto.setTemplateId(template.getId());
            responseDto.setTemplateName(template.getTemplateName());
            List<Long> itemIdList = template.getItemList().stream()
                    .map(Item::getId)
                    .collect(Collectors.toList());
            responseDto.setItemIdList(itemIdList);
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public void updateTeamScore(Long teamId, int score) {
        Team team = teamRepository.findById(teamId).orElseThrow(
                () -> new CustomException(ResponseCode.TEAM_NOT_FOUND)
        );

        team.updateScore(score);
        teamRepository.save(team);
    }

    public ItemResponseDto getNextItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ResponseCode.ITEM_NOT_FOUND));
        item.setWatched(true);
        String answer = item.getAnswer();
        String itemType = null;
        String musicUrl = null;
        String characterUrl = null;
        String consonantQuestion = null;
        if (item instanceof Music) {
            musicUrl = ((Music) item).getMusicUrl();
            itemType = "MUSIC";
        } else if (item instanceof Consonant) {
            consonantQuestion = ((Consonant) item).getConsonantQuestion();
            itemType = "CONSONANT";
        } else if (item instanceof Character) {
            characterUrl = ((Character) item).getCharacterUrl();
            itemType = "CHARACTER";
        }

        return new ItemResponseDto(itemType, answer, characterUrl, consonantQuestion, musicUrl);
    }

    public List<TeamScoreResponseDto> getPresentationResult(Long presentationId) {
        return teamRepository.findAllByPresentationId(presentationId).stream()
                .filter(team -> Objects.equals(team.getPresentation().getId(), presentationId))
                .map(team -> new TeamScoreResponseDto(team.getTeamName(), team.getScore()))
                .collect(Collectors.toList());
    }

    public PresentationResumeResponseDto getResumed(Long presentationId) {
        Long currentItemId = 0L;
        try {
            List<Template> templateList = presentationRepository.findById(presentationId).get().getTemplateList();
            List<TemplatePostResponseDto> templatePostResponseDtos = getTemplatePostResponseDto(templateList);
            for (int i = 0; i < templateList.size(); i++) {
                for (int j = 0; j < templateList.get(i).getItemList().size(); j++) {
                    if (!templateList.get(i).getItemList().get(j).isWatched()) {
                        currentItemId = templateList.get(i).getItemList().get(i).getId();
                        return new PresentationResumeResponseDto(currentItemId, templatePostResponseDtos);
                    }
                }
            }
            return new PresentationResumeResponseDto(currentItemId, templatePostResponseDtos);
        } catch (Exception e) {
            throw new CustomException(ResponseCode.TEMPLATE_NOT_FOUND);
        }

    }
}
