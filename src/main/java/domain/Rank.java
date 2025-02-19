package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum Rank {
    NONE(Prize.NONE, MatchCount.NONE, false),
    FIFTH(Prize.FIFTH, MatchCount.THREE, false),
    FOURTH(Prize.FOURTH, MatchCount.FOUR, false),
    THIRD(Prize.THIRD, MatchCount.FIVE, false),
    SECOND(Prize.SECOND, MatchCount.FIVE, true),
    FIRST(Prize.FIRST, MatchCount.SIX, false);

    public static final int DEFAULT_COUNT = 0;
    private static final int INCREMENT = 1;

    private final long prize;
    private final int count;
    private final boolean bonusMatch;

    Rank(long prize, int count, boolean bonusMatch) {
        this.prize = prize;
        this.count = count;
        this.bonusMatch = bonusMatch;
    }

    private static final class Prize {
        private static final long NONE = 0L;
        private static final long FIFTH = 5_000L;
        private static final long FOURTH = 50_000L;
        private static final long THIRD = 1_500_000L;
        private static final long SECOND = 30_000_000L;
        private static final long FIRST = 2_000_000_000L;
    }

    private static final class MatchCount {
        private static final int NONE = 0;
        private static final int THREE = 3;
        private static final int FOUR = 4;
        private static final int FIVE = 5;
        private static final int SIX = 6;
    }

    public static Rank fromResult(int matchCount, boolean contains) {
        List<Rank> ranks = Arrays.stream(Rank.values()).toList().reversed();
        return ranks.stream()
                .filter(rank -> rank.getCount() == matchCount && (!rank.isBonusMatch() || contains))
                .findFirst()
                .orElse(NONE);
    }

    public static LottoStats makeLottoResult(List<Lotto> lottos, List<Integer> winningNumbers, int bonusBall){
        Map<Rank,Integer> ranks = new TreeMap<>();
        for (Lotto lotto : lottos) {
            Rank lottoRank = lotto.getRank(winningNumbers, bonusBall);
            ranks.put(lottoRank, ranks.getOrDefault(lottoRank, DEFAULT_COUNT) + INCREMENT);
        }
        return new LottoStats(ranks);
    }

    public Long getPrize() {
        return prize;
    }

    public int getCount() {
        return count;
    }

    public boolean isBonusMatch() {
        return bonusMatch;
    }

    public boolean isSecond() {
        return this == Rank.SECOND;
    }

    public boolean isNotNone() {
        return this != Rank.NONE;
    }
}
