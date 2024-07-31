package com.sparcs10.demo.service;

import com.sparcs10.demo.controller.requestDto.SuccessQuestRequest;
import com.sparcs10.demo.controller.responseDto.UserQuestDto;
import com.sparcs10.demo.entity.Quest;
import com.sparcs10.demo.entity.User;
import com.sparcs10.demo.entity.UserQuest;
import com.sparcs10.demo.repository.QuestRepository;
import com.sparcs10.demo.repository.UserQuestRepository;
import com.sparcs10.demo.repository.UserRepository;
import com.sparcs10.demo.utils.QuestStatus;
import com.sparcs10.demo.utils.Reward;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserQuestService {

    private final QuestRepository questRepository;
    private final UserQuestRepository userQuestRepository;
    private final UserRepository userRepository;
//    private final UserRepository userRepository;

    public UserQuestDto success(SuccessQuestRequest request) {
        Quest quest = questRepository.findByContent(request.getQuestContent());
        if (quest == null) {
            throw new IllegalArgumentException("Quest not found");
        }
        User user = userRepository.findByUsername(request.getUserName());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        UserQuest userQuest = UserQuest.builder()
                .user(user)
                .quest(quest)
                .status(QuestStatus.COMPLETED)
                .today(LocalDate.now())
                .build();
        userQuestRepository.save(userQuest);
        return UserQuestDto.fromEntity(userQuest);
    }

    public int todayProgress(String userName) {
        return userQuestRepository.countAllByUser_UsernameAndSuccessDate(userName, LocalDate.now());
    }
}
