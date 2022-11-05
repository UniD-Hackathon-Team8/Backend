package com.meltingzone.meltingzone.controller;

import com.meltingzone.meltingzone.dto.game.GameRequestDto;
import com.meltingzone.meltingzone.service.GameService;
import com.meltingzone.meltingzone.util.ResponseCode;
import com.meltingzone.meltingzone.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<ResponseMessage> createGame(@RequestBody GameRequestDto requestDto) {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                gameService.createGame(requestDto)
        );
    }

    @PostMapping("/own")
    public ResponseEntity<ResponseMessage> readAllOwnGames() {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                gameService.readAllOwnGame()
        );
    }
}
