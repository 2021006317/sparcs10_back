package com.sparcs10.demo.controller;

import com.sparcs10.demo.controller.requestDto.SuccessQuestRequest;
import com.sparcs10.demo.controller.responseDto.UserQuestDto;
import com.sparcs10.demo.service.UserQuestService;
import com.sparcs10.demo.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-quest")
@RequiredArgsConstructor
public class UserQuestController {
    private final UserQuestService userQuestService;

    @PostMapping("/success")
    public ResponseEntity<CustomResponse<UserQuestDto>> success(@RequestBody @Validated SuccessQuestRequest request) {
        return ResponseEntity.ok(
            CustomResponse.okresponse(userQuestService.success(request))
        );
    }

    @GetMapping("/today-progress/count")
    public ResponseEntity<CustomResponse<Integer>> todayProgress(@RequestParam String userName) {
        return ResponseEntity.ok(
            CustomResponse.okresponse(userQuestService.todayProgressCount(userName))
        );
    }

    @GetMapping("/today-progress/list")
    public ResponseEntity<CustomResponse<List<UserQuestDto>>> todayProgressList(@RequestParam String userName) {
        return ResponseEntity.ok(
            CustomResponse.okresponse(userQuestService.todayProgressList(userName))
        );
    }
}
