package com.sparcs10.demo.controller;

import com.sparcs10.demo.dto.TrashcanDTO;
import com.sparcs10.demo.service.TrashcanService;
import com.sparcs10.demo.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trashcan")
@RequiredArgsConstructor
public class TrashcanController {
    private final TrashcanService trashcanService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<TrashcanDTO>>> list() {
        return new ResponseEntity<>(
                CustomResponse.response(trashcanService.list()),
                HttpStatus.OK
        );
    }
}
