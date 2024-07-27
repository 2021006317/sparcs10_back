package com.sparcs10.demo.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@JsonDeserialize
public class QuestCreateRequest {
    private String content;
    private String solution;
    private Reward reward;
    private String type;

    public QuestCreateRequest(String content, String solution, Reward reward, String type) {
        this.content = content;
        this.solution = solution;
        this.reward = reward;
        this.type = type;
    }
}
