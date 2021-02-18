package lotto.domain.lotto;

import java.util.Arrays;

public enum LottoRank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    SIXTH(2, 0),
    SEVENTH(1, 0),
    NONE(0, 0),

    ;

    private final int numMatch;
    private final int winningMoney;

    LottoRank(int numMatch, int winningMoney) {
        this.numMatch = numMatch;
        this.winningMoney = winningMoney;
    }

    public static LottoRank checkRank(int numMatch, boolean bonus) {
        if (numMatch == SECOND.numMatch && bonus) {
            return SECOND;
        }
        return Arrays.stream(values()).filter(value -> value.numMatch == numMatch).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("잘못된 값입니다."));
    }
}
