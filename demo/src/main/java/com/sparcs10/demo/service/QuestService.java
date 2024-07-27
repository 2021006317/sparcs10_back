package com.sparcs10.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparcs10.demo.dto.QuestDTO;
import com.sparcs10.demo.entity.Quest;
import com.sparcs10.demo.repository.QuestRepository;
import com.sparcs10.demo.utils.QuestCreateRequest;
import com.sparcs10.demo.utils.Reward;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestService {
    private final QuestRepository questRepository;

    public List<QuestDTO> list() {
        return questRepository.findAll().stream()
                .map(QuestDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public List<QuestDTO> dailyList() {
        return questRepository.findByType("daily").stream()
                .map(QuestDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public String dailySolution(String id) {
        return questRepository.findById(id)
                .map(QuestDTO::fromEntity)
                .map(QuestDTO::getSolution)
                .orElseThrow(() -> new IllegalArgumentException("Quest not found"));
    }

    public QuestDTO create(QuestCreateRequest request) {
        Reward reward = request.getReward();
        QuestCreateRequest newRequest = new QuestCreateRequest(request.getContent(), request.getSolution(), reward, request.getType());
        Quest quest = Quest.builder()
                .content(newRequest.getContent())
                .solution(newRequest.getSolution())
                .reward(reward)
                .type(newRequest.getType())
                .build();
        questRepository.save(quest);
        return QuestDTO.fromEntity(quest);
    }
}
