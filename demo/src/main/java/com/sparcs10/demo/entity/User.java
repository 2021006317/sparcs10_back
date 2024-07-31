package com.sparcs10.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Long coin;

    @Column
    private Long leaf;

}
