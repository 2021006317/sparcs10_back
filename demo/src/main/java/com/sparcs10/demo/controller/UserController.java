package com.sparcs10.demo.controller;

import com.sparcs10.demo.service.UserService;
import com.sparcs10.demo.utils.CustomResponse;
import com.sparcs10.demo.utils.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/money")
    public ResponseEntity<CustomResponse<Reward>> getMoney(@RequestParam String userName) {
        return ResponseEntity.ok(
            CustomResponse.okresponse(userService.getMoney(userName))
        );
    }
}
