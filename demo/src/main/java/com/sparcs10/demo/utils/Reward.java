package com.sparcs10.demo.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Reward {
    private int coin;
    private int leaf;

    public Reward(int coin, int leaf) {
        this.coin = coin;
        this.leaf = leaf;
    }
}
