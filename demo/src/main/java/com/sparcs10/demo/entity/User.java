package com.sparcs10.demo.entity;

import com.sparcs10.demo.utils.Reward;
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

    public Reward getMoney(){
        int coinToReturn = coin == null ? 0 : Math.toIntExact(coin);
        int leafToReturn = leaf == null ? 0 : Math.toIntExact(leaf);
        return new Reward(coinToReturn, leafToReturn);
    }

}
