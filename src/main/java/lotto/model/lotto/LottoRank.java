package lotto.model.lotto;

import java.util.Arrays;

public enum LottoRank {
    MISS(0, 0),
    FIFTH(3, 5_000),
    FOURTH(4, 50_000),
    THIRD(5, 1_500_000),
    SECOND(5, 30_000_000),
    FIRST(6, 2_000_000_000);

    private static final String MISS_PRINT_MESSAGE = "낙첨";
    private static final String SECOND_PRINT_MESSAGE = ", 보너스 볼 일치";
    private static final String COUNT_OF_MATCH_PRINT_FORMAT = "%d개 일치";
    private int countOfMatch;
    private long winningMoney;

    LottoRank(int countOfMatch, long winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public long calculateWinningMoney(long countOfMatchingLotto) {
        return winningMoney * countOfMatchingLotto;
    }

    public static LottoRank findRank(int countOfMatch, boolean hasBonusNumber) {
        if (Integer.valueOf(countOfMatch).equals(SECOND.countOfMatch)) {
            return hasBonusNumber ? SECOND : THIRD;
        }
        return Arrays.stream(LottoRank.values())
                .filter(lottoRank -> lottoRank.matchCount(countOfMatch))
                .findFirst()
                .orElse(MISS);
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }

    @Override
    public String toString() {
        if (this.equals(MISS)) {
            return MISS_PRINT_MESSAGE;
        }
        StringBuilder stringBuilder = new StringBuilder(String.format(COUNT_OF_MATCH_PRINT_FORMAT, countOfMatch));
        if (this.equals(SECOND)) {
            stringBuilder.append(SECOND_PRINT_MESSAGE);
        }

        return stringBuilder.toString();
    }
}
