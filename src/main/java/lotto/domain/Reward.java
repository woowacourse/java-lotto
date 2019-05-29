package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static Reward valueOf(int matchNumber) {
        List<Reward> matches = Arrays.stream(values())
                .filter(reward -> reward.match(matchNumber))
                .collect(Collectors.toList());
        if (matches.size() == 0) {
            return LOSE;
        }
        if (matches.size() != 1) {
            throw new IllegalArgumentException();
        }
        return matches.get(0);
    }

    public static Reward valueOf(int matchNumber, boolean bonus) {
        return SECOND;
    }

    private boolean match(int count) {
        return this.count == count;
    }


    public int money() {
        return money;
    }

    @Override
    public String toString() {
        if (bonus) {
            return count + "개 일치, 보너스 볼 일치(" + money + ") ";
        }
        return count + "개 일치 " + "(" + money + ")";
    }
}
