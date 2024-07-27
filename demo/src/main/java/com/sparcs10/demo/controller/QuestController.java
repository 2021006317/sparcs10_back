package com.sparcs10.demo.controller;

import com.sparcs10.demo.dto.QuestDTO;
import com.sparcs10.demo.service.QuestService;
import com.sparcs10.demo.utils.CustomResponse;
import com.sparcs10.demo.controller.requestDto.QuestCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
public class QuestController {
    private final QuestService questService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<QuestDTO>>> list() {
        return new ResponseEntity<>(
                CustomResponse.response(questService.list()),
                HttpStatus.OK);
    }

    @GetMapping("/daily/list")
    public ResponseEntity<CustomResponse<List<QuestDTO>>> dailyList() {
        return new ResponseEntity<>(
                CustomResponse.response(questService.dailyList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}/solution")
    public ResponseEntity<CustomResponse<String>> dailySolution(@PathVariable String id) {
        return new ResponseEntity<>(
                CustomResponse.response(questService.dailySolution(id)),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<QuestDTO>> create(@RequestBody @Validated QuestCreateRequest request) {
        return new ResponseEntity<>(
                CustomResponse.response(questService.create(request)),
                HttpStatus.CREATED);
    }
}
