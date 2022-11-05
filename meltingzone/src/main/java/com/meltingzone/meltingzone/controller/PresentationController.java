package com.meltingzone.meltingzone.controller;

import com.meltingzone.meltingzone.dto.game.GameRequestDto;
import com.meltingzone.meltingzone.dto.presentation.PresentationPostRequestDto;
import com.meltingzone.meltingzone.service.PresentationService;
import com.meltingzone.meltingzone.util.ResponseCode;
import com.meltingzone.meltingzone.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/presentation")
@RequiredArgsConstructor
public class PresentationController {
    private final PresentationService presentationService;

    @PostMapping
    public ResponseEntity<ResponseMessage> createGame(
            @RequestBody PresentationPostRequestDto requestDto,
            HttpServletRequest request) {
        String email = request.getUserPrincipal().getName();
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                presentationService.createPresentation(requestDto, email)
        );
    }
}
