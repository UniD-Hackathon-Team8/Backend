package com.meltingzone.meltingzone.controller;

import com.meltingzone.meltingzone.dto.game.GameRequestDto;
import com.meltingzone.meltingzone.service.GameService;
import com.meltingzone.meltingzone.util.ResponseCode;
import com.meltingzone.meltingzone.util.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/own")
    public ResponseEntity<ResponseMessage> readAllOwnGames() {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                gameService.readAllOwnGame()
        );
    }

    @GetMapping("/custom")
    public ResponseEntity<ResponseMessage> readAllCustomGames() {
        return ResponseMessage.toResponseEntity(
                ResponseCode.OK,
                gameService.readAllOwnGame()
        );
    }
}
