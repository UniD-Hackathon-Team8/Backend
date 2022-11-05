package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.auth.CustomUserDetailsService;
import com.meltingzone.meltingzone.domain.Presentation;
import com.meltingzone.meltingzone.domain.Team;
import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.domain.User;
import com.meltingzone.meltingzone.domain.item.Item;
import com.meltingzone.meltingzone.dto.presentation.PresentationPostRequestDto;
import com.meltingzone.meltingzone.dto.presentation.PresentationPostResponseDto;
import com.meltingzone.meltingzone.dto.presentation.TeamPostResponseDto;
import com.meltingzone.meltingzone.dto.presentation.TemplatePostResponseDto;
import com.meltingzone.meltingzone.repository.PresentationRepository;
import com.meltingzone.meltingzone.repository.TeamRepository;
import com.meltingzone.meltingzone.repository.TemplateRepository;
import com.meltingzone.meltingzone.repository.UserRepository;
import com.meltingzone.meltingzone.util.CustomException;
import com.meltingzone.meltingzone.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PresentationService {
    private final UserRepository userRepository;
    private final TemplateRepository templateRepository;
    private final TeamRepository teamRepository;
    private final PresentationRepository presentationRepository;
    private final CustomUserDetailsService customUserDetailsService;

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
            Team team = new Team();
            team.setTeamName(i + "조");
            team.setPresentation(presentation);
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
}
