package com.meltingzone.meltingzone.controller;

import com.meltingzone.meltingzone.dto.game.GameRequestDto;
import com.meltingzone.meltingzone.dto.presentation.PresentationPostRequestDto;
import com.meltingzone.meltingzone.service.PresentationService;
import com.meltingzone.meltingzone.util.ResponseCode;
import com.meltingzone.meltingzone.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/template/{itemId}")
    public ResponseEntity<ResponseMessage> changeToNextItem(@PathVariable Long itemId) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                presentationService.getNextItem(itemId)
        );
    }

    @GetMapping("/{presentationId}")
    public ResponseEntity<ResponseMessage> getTeamScore(@PathVariable Long presentationId) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                presentationService.getPresentationResult(presentationId)
        );
    }

    @GetMapping("/show/{presentationId}")
    public ResponseEntity<ResponseMessage> getResumed(@PathVariable Long presentationId) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                presentationService.getResumed(presentationId)
        );
    }
}
