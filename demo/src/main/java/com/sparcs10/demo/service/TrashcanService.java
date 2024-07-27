package com.sparcs10.demo.service;

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

    public TrashcanDTO create(String address, List<String> trashTypes){
        String types = String.join(",", trashTypes);
        return TrashcanDTO.fromEntity(trashcanRepository.save(new Trashcan(address, types)));
    }
}
