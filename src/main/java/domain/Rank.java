package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum Rank {
    NONE("", Prize.NONE, MatchCount.NONE, false),
    FIFTH("3개 일치 (5,000원)- ", Prize.FIFTH, MatchCount.THREE, false),
    FOURTH("4개 일치 (50,000원)- ", Prize.FOURTH, MatchCount.FOUR, false),
    THIRD("5개 일치 (1,500,000원)- ", Prize.THIRD, MatchCount.FIVE, false),
    SECOND("5개 일치, 보너스 볼 일치 (30,000,000원)- ", Prize.SECOND, MatchCount.FIVE, true),
    FIRST("6개 일치 (2,000,000,000원)- ", Prize.FIRST, MatchCount.SIX, false);

    public static final int DEFAULT_COUNT = 0;
    private static final int INCREMENT = 1;

    private final String message;
    private final long prize;
    private final int count;
    private final boolean bonusMatch;

    Rank(String message, long prize, int count, boolean bonusMatch) {
        this.message = message;
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

    public String getMessage() {
        return message;
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
}
