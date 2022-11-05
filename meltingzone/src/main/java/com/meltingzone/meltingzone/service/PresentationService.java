package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.domain.Presentation;
import com.meltingzone.meltingzone.domain.Template;
import com.meltingzone.meltingzone.repository.PresentationRepository;
import com.meltingzone.meltingzone.repository.TeamRepository;
import com.meltingzone.meltingzone.repository.TemplateRepository;
import com.meltingzone.meltingzone.util.CustomException;
import com.meltingzone.meltingzone.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PresentationService {
    private final PresentationRepository presentationRepository;
    private final TemplateRepository templateRepository;
    private final TeamRepository teamRepository;

    public void savePresentation(List<Long> templateIdList, int teamCount) {
        Presentation presentation = new Presentation();
        List<Template> templateList = templateIdList.stream()
                .map(id -> templateRepository.findById(id)
                        .orElseThrow(
                                () -> new CustomException(ResponseCode.TEMPLATE_NOT_FOUND)
                        )
                )
                .collect(Collectors.toList());



        presentation
    }

    private T
}
