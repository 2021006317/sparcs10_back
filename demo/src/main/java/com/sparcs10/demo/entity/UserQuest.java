package com.sparcs10.demo.entity;

import com.sparcs10.demo.utils.QuestStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_quest")
public class UserQuest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private QuestStatus status; // enum으로 관리

    @Column
    private Date successDate;

    @Builder
    public UserQuest(User user, Quest quest, QuestStatus status) {
        this.user = user;
        this.quest = quest;
        this.status = status;
    }
}
