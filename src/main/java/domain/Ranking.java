package domain;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public enum Ranking {
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

    Ranking(int matching, boolean bonusMatching, int money) {
        this.matching = matching;
        this.bonusMatching = bonusMatching;
        this.money = money;
    }

    public static Ranking select(int matching, boolean bonusMatching) {
        final Stream<Ranking> rankingStream = Arrays.stream(Ranking.values())
                .filter(ranking -> Objects.equals(matching, ranking.matching));
        if (matching >= BONUS_CONSIDER_LIMIT) {
            return considerBonus(bonusMatching, rankingStream);
        }
        return notConsiderBonus(rankingStream);
    }

    private static Ranking notConsiderBonus(Stream<Ranking> rankingStream) {
        return rankingStream
                .findFirst()
                .orElse(NOTHING);
    }

    private static Ranking considerBonus(boolean bonusMatching, Stream<Ranking> rankingStream) {
        return rankingStream
                .filter(ranking -> Objects.equals(bonusMatching, ranking.bonusMatching))
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
