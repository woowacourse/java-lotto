package domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public enum Prize {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5000),
    NOTHING(0, false, 0);

    private static final int BONUS_CONSIDER_LIMIT = 5;

    private final int matching;
    private final boolean bonusMatching;
    private final int money;

    Prize(final int matching, final boolean bonusMatching, final int money) {
        this.matching = matching;
        this.bonusMatching = bonusMatching;
        this.money = money;
    }

    public static Prize select(final int matching, final boolean bonusMatching) {
        final Stream<Prize> rankingStream = Arrays.stream(Prize.values())
                .filter(prize -> Objects.equals(matching, prize.matching));

        if (matching >= BONUS_CONSIDER_LIMIT) {
            return considerBonus(bonusMatching, rankingStream);
        }
        return notConsiderBonus(rankingStream);
    }

    private static Prize notConsiderBonus(final Stream<Prize> rankingStream) {
        return rankingStream
                .findFirst()
                .orElse(NOTHING);
    }

    private static Prize considerBonus(final boolean bonusMatching, final Stream<Prize> rankingStream) {
        return rankingStream
                .filter(prize -> Objects.equals(bonusMatching, prize.bonusMatching))
                .findFirst()
                .orElse(NOTHING);
    }

    public int getMoney() {
        return money;
    }

    public int getMatching() {
        return matching;
    }
}
