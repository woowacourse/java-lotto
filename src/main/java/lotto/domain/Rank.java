package lotto.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final int countOfMatch;
    private final long winningMoney;

    Rank(int countOfMatch, long winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch == 1 || countOfMatch == 2) {
            return MISS;
        }

        if (SECOND.match(countOfMatch) && matchBonus) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.match(countOfMatch))
                .filter(Rank::isNotSecond)
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    private boolean match(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    public boolean isMiss() {
        return this == MISS;
    }

    public boolean isNotSecond() {
        return this != SECOND;
    }

    public static long calculateTotalWinningMoney(Map<Rank, Integer> lottoScore) {
        if (Objects.isNull(lottoScore) || lottoScore.isEmpty()) {
            throw new NullPointerException();
        }

        return lottoScore.keySet().stream()
                .mapToLong(rank -> lottoScore.get(rank) * rank.winningMoney)
                .sum();
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public long getWinningMoney() {
        return winningMoney;
    }
}
