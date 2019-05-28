package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Reward {
    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    LOSE(2, 0);

    private final int count;
    private final int money;

    private Reward(int count, int money) {
        this.count = count;
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

    private boolean match(int count) {
        return this.count == count;
    }


    public int money() {
        return money;
    }
}
