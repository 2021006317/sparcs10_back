package com.sparcs10.demo.dto;

import com.sparcs10.demo.entity.Quest;
import com.sparcs10.demo.utils.Reward;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuestDTO {
    private String id;
    private String content;
    private String solution;
    private Reward reward;
    private String type;

    public static QuestDTO fromEntity(Quest entity) {
        QuestDTO dto = new QuestDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setSolution(entity.getSolution());
        int rewardCoin = entity.getRewardCoin() == null ? 0 : entity.getRewardCoin();
        int rewardLeaf = entity.getRewardLeaf() == null ? 0 : entity.getRewardLeaf();
        dto.setReward(new Reward(rewardCoin, rewardLeaf));
        dto.setType(entity.getType());
        return dto;
    }

    public Quest toEntity() {
        return new Quest(content, solution, reward, type);
    }
}
