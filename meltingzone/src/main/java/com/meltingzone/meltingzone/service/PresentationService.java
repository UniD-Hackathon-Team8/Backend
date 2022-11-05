package com.meltingzone.meltingzone.service;

import com.meltingzone.meltingzone.repository.PresentationRepository;
import com.meltingzone.meltingzone.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PresentationService {
    private final PresentationRepository presentationRepository;
    private final TeamRepository teamRepository;
}
