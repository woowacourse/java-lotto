package lotto.domain;

import java.util.Arrays;

public enum LottoMatchKind {
    THREE(3, false),
    FOUR(4, false),
    FIVE(5, false),
    FIVE_BONUS(5, true),
    SIX(6, false);

    private final int matchCount;
    private final boolean bonus;

    LottoMatchKind(final int matchCount, final boolean bonus) {
        this.matchCount = matchCount;
        this.bonus = bonus;
    }

    public static LottoMatchKind from(final int matchCount, final boolean bonus) {
        return Arrays.stream(values())
                .filter(matchKind -> matchKind.matchCount == matchCount)
                .filter(matchKind -> matchKind.bonus == bonus)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("매치 개수는 3개 미만이거나 6개 초과입니다."));
    }
}
