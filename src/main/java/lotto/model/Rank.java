package lotto.model;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6),
    SECOND(5),
    THIRD(5),
    FOURTH(4),
    FIFTH(3),
    ;

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank parse(int matchWinningNumbers, boolean matchBonus) {
        if (matchBonus && matchWinningNumbers == 5) {
            return Rank.SECOND;
        }
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value == matchWinningNumbers && rank != Rank.SECOND)
                .findFirst().orElseThrow(NoSuchElementException::new);
    }


}
