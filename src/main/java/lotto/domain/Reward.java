package lotto.domain;

import java.util.Arrays;

public enum Reward {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    LOSE(2, false, 0);

    private final int count;
    private final boolean bonus;
    private final int money;


    private Reward(int count, boolean bonus, int money) {
        this.count = count;
        this.bonus = bonus;
        this.money = money;
    }

    public static Reward valueOf(int matchNumber, boolean bonus) {
        return Arrays.stream(values())
                .filter(reward -> reward.match(matchNumber, bonus))
                .findFirst()
                .orElse(valueOf("LOSE"));
    }

    private boolean match(int count, boolean bonus) {
        return this.count == count && this.bonus == bonus;
    }


    public int money() {
        return money;
    }

    @Override
    public String toString() {
        if (bonus) {
            return count + "개 일치, 보너스 볼 일치(" + money + ") ";
        }
        return count + "개 일치 " + "(" + money + ") ";
    }
}
