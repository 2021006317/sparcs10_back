package com.sparcs10.demo.controller.responseDto;

import com.sparcs10.demo.entity.UserQuest;
import com.sparcs10.demo.utils.Reward;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
public class UserQuestDto {
    private String questName;
    private String questStatus;
    private String userName;
    private Reward reward;
    private LocalDate successDate;

    @Builder
    public UserQuestDto(String questName, String questStatus, String userName, Reward reward, LocalDate successDate) {
        this.questName = questName;
        this.questStatus = questStatus;
        this.userName = userName;
        this.reward = reward;
        this.successDate = successDate;
    }

    public static UserQuestDto fromEntity(UserQuest userQuest) {
        return UserQuestDto.builder()
                .questName(userQuest.getQuest().getContent())
                .questStatus(userQuest.getStatus().name())
                .userName(userQuest.getUser().getUsername())
                .reward(userQuest.getQuest().getReward())
                .successDate(userQuest.getSuccessDate())
                .build();
    }
}
