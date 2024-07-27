package com.sparcs10.demo.service;

import com.sparcs10.demo.controller.requestDto.TrashcanCreateRequest;
import com.sparcs10.demo.dto.TrashcanDTO;
import com.sparcs10.demo.entity.Trashcan;
import com.sparcs10.demo.repository.TrashcanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrashcanService {
    private final TrashcanRepository trashcanRepository;

    public List<TrashcanDTO> list() {
        return trashcanRepository.findAll().stream()
                .map(TrashcanDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public TrashcanDTO create(TrashcanCreateRequest request) {
        List<String> types = List.of(request.getTypes().split("-"));
        Trashcan trashcan = Trashcan.builder()
                .address(request.getAddress())
                .types(String.join(",", types))
                .build();
        trashcanRepository.save(trashcan);
        return TrashcanDTO.fromEntity(trashcan);
    }
}
