package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2000000000),
    SECOND(5, true, 30000000),
    THIRD(5, false, 1500000),
    FOURTH(4, false, 50000),
    FIFTH(3, false, 5000),
    LOSE(2, false, 0);


    private final int match;
    private final boolean bonus;
    private final int money;

    Rank(int match, boolean bonus, int money) {
        this.match = match;
        this.bonus = bonus;
        this.money = money;
    }

    public static Rank rank(int match, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.match == match && rank.bonus == bonus)
                .findAny()
                .orElse(LOSE);
    }

    public int money() {
        return money;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(match).append("개 일치 ");
        if (bonus) {
            stringBuilder.append("보너스 볼 일치 ").append(money);
            return stringBuilder.toString();
        }
        return stringBuilder.append(money).toString();
    }
}
