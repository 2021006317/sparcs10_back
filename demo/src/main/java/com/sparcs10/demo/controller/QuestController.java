package com.sparcs10.demo.controller;

import com.sparcs10.demo.dto.QuestDTO;
import com.sparcs10.demo.service.QuestService;
import com.sparcs10.demo.utils.QuestCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quest")
@RequiredArgsConstructor
public class QuestController {
    private final QuestService questService;

    @GetMapping("/list")
    public List<QuestDTO> list() {
        return questService.list();
    }

    @GetMapping("/daily/list")
    public List<QuestDTO> dailyList() {
        return questService.dailyList();
    }

    @GetMapping("/{id}/solution")
    public String dailySolution(@PathVariable String id) {
        return questService.dailySolution(id);
    }

    @PostMapping("/create")
    public QuestDTO create(@RequestBody QuestCreateRequest request) {
        return questService.create(request);
    }
}
