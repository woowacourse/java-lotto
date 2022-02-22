package lotto.model;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    ;

    private final int value;
    private final long money;

    Rank(int value, long money) {
        this.value = value;
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

    public static Rank parse(int matchWinningNumbers, boolean matchBonus) {
        if (matchBonus && matchWinningNumbers == SECOND.value) {
            return Rank.SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == matchWinningNumbers && rank != Rank.SECOND)
                .findFirst().orElseThrow(NoSuchElementException::new);
    }


}
