package com.sparcs10.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "challenge")
public class Challenge { // 업적
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int point;

    @Column
    private String type; // 업적의 종류. 일일, 주간, 월간, 누적 등
}
