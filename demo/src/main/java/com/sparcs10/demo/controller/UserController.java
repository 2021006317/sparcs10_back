package com.sparcs10.demo.controller;

import com.sparcs10.demo.service.UserService;
import com.sparcs10.demo.utils.CustomResponse;
import com.sparcs10.demo.utils.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/money")
    public ResponseEntity<CustomResponse<Reward>> getMoney(@RequestParam String userName) {
        return ResponseEntity.ok(
            CustomResponse.response(userService.getMoney(userName))
        );
    }

    @PutMapping("/use-money")
    public ResponseEntity<CustomResponse> useMoney(@RequestParam String moneyType, @RequestParam int amount, @RequestParam String userName) {
        try{
            Reward res = userService.useMoney(moneyType, amount, userName);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(
                CustomResponse.response(HttpStatus.BAD_REQUEST, e.getMessage())
            );
        }
        return ResponseEntity.ok(
            CustomResponse.response(userService.useMoney(moneyType, amount, userName))
        );
    }
}
