package lotto.domain;

import java.util.Arrays;

public enum Rank {
    NO_REWARD(0, false, 0, null),
    FIFTH(3, false, 5000, "3개 일치 (5000원)- "),
    FOURTH(4, false, 50_000, "4개 일치 (50000원)- "),
    THIRD(5, false, 1_500_000, "5개 일치 (1500000원)- "),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치(30000000원) -"),
    FIRST(6, false, 2_000_000_000, "6개 일치 (2000000000원)- ");

    private static final int CHECK_BONUS_NUMBER_COUNT = 5;
    private final int correctCount;
    private final boolean hasBonusNumber;
    private final long prize;
    private final String message;

    Rank(int correctCount, boolean hasBonusNumber, long prize, String message) {
        this.correctCount = correctCount;
        this.hasBonusNumber = hasBonusNumber;
        this.prize = prize;
        this.message = message;
    }

    public static Rank find(int correctCount, boolean hasBonusNumber) {
        return Arrays.stream(values())
                .filter(rank -> rank.filter(correctCount, hasBonusNumber))
                .findFirst()
                .orElse(NO_REWARD);
    }


    private boolean filter(int correctCount, boolean hasBonusNumber) {
        if (correctCount == CHECK_BONUS_NUMBER_COUNT) {
            return correctCount == this.correctCount && hasBonusNumber == this.hasBonusNumber;
        }

        return correctCount == this.correctCount;
    }

    public long getPrize() {
        return prize;
    }

    public String getMessage() {
        return message;
    }
}
