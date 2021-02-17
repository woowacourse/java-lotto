package lotto.domain.lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(1, 6, false),
    SECOND(2, 5, true),
    THIRD(3, 5, false),
    FOURTH(4, 4, false),
    FIFTH(5, 3, false);

    private final int rank;
    private final int matchedNumber;
    private final boolean hasBonusNumber;

    Rank(int rank, int matchedNumber, boolean hasBonusNumber) {
        this.rank = rank;
        this.matchedNumber = matchedNumber;
        this.hasBonusNumber = hasBonusNumber;
    }

    public int getRank() {
        return rank;
    }

    public static Rank getRank(int matchedNumber, boolean hasBonusNumber) {
        return Arrays.stream(Rank.values())
            .filter(r -> r.matchedNumber == matchedNumber && r.hasBonusNumber == hasBonusNumber)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("매칭되는 등수가 없습니다."));
    }
}
