package com.sparcs10.demo.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Reward {
    private int coin;
    private int gem;

    public Reward(int coin, int gem) {
        this.coin = coin;
        this.gem = gem;
    }
}
