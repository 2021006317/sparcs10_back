package com.sparcs10.demo.controller;

import com.sparcs10.demo.controller.requestDto.TrashcanCreateRequest;
import com.sparcs10.demo.dto.TrashcanDTO;
import com.sparcs10.demo.service.TrashcanService;
import com.sparcs10.demo.utils.CustomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<CustomResponse<TrashcanDTO>> create(@RequestBody @Validated TrashcanCreateRequest request) {
        return new ResponseEntity<>(
                CustomResponse.response(trashcanService.create(request)),
                HttpStatus.CREATED
        );
    }
}
