package lotto.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LottoRank {
    FIFTH(new MatchCount(3), false, new Money(5000)),
    FOURTH(new MatchCount(4), false, new Money(50000)),
    THIRD(new MatchCount(5), false, new Money(1_500_000)),
    SECOND(new MatchCount(5), true, new Money(30_000_000)),
    FIRST(new MatchCount(6), false, new Money(2_000_000_000));

    private static final String THERE_IS_NON_RANK_EXCEPTION_MESSAGE = "꽝!";

    private final MatchCount matchCount;
    private final boolean bonusMatch;
    private final Money money;

    LottoRank(MatchCount matchCount, boolean bonusMatch, Money money) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.money = money;
    }

    public static LottoRank getRank(int matchCount, boolean bonusMatch) {
        List<LottoRank> lottoRanks = Arrays.stream(values())
            .filter(lottoRank -> lottoRank.matchCount.getMatchCount() == matchCount)
            .sorted()
            .collect(Collectors.toList());
        return getRankWithBonus(lottoRanks, bonusMatch);
    }

    private static LottoRank getRankWithBonus(List<LottoRank> lottoRanks, boolean bonusMatch) {
        if (lottoRanks.size() == 1) {
            return lottoRanks.get(0);
        }
        return lottoRanks.stream()
            .filter(lottoRank -> lottoRank.bonusMatch == bonusMatch)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(THERE_IS_NON_RANK_EXCEPTION_MESSAGE));
    }

    public static boolean isPrizeCount(int matchCount) {
        return Arrays.stream(values())
            .anyMatch(rank -> rank.matchCount.getMatchCount() == matchCount);
    }

    public long getTotal(int count) {
        return this.money.multiply(count);
    }

    public int getMatchCount() {
        return this.matchCount.getMatchCount();
    }

    public int getMoney() {
        return this.money.getMoney();
    }
}
