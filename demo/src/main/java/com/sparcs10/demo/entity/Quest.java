package com.sparcs10.demo.entity;

import com.sparcs10.demo.controller.requestDto.QuestCreateRequest;
import com.sparcs10.demo.utils.Reward;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@NoArgsConstructor
@Table(name = "quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String content;

    @Column
    private String solution;

    @Column
    private int rewardCoin;

    @Column
    private int rewardGem;

    @Column
    private String type;

    @Builder
    public Quest(String content, String solution, Reward reward, String type) {
        this.content = content;
        this.solution = solution;
        this.rewardCoin = reward.getCoin();
        this.rewardGem = reward.getGem();
        this.type = type;
    }

    @Builder
    public Quest(QuestCreateRequest request) {
        this.content = request.getContent();
        this.solution = request.getSolution();
        this.rewardCoin = request.getReward().getCoin();
        this.rewardGem = request.getReward().getGem();
        this.type = request.getType();
    }
}
