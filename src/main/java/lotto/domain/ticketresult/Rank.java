package lotto.domain.ticketresult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    THREE_MATCH(3, 5_000),
    FOUR_MATCH(4, 50_000),
    FIVE_MATCH(5, 1_500_000),
    FIVE_AND_BONUS_MATCH(5, 30_000_000),
    SIX_MATCH(6, 2_000_000_000);

    private final int countMatchedNumbers;
    private final int prizeMoney;

    Rank(int countMatchedNumbers, int prizeMoney) {
        this.countMatchedNumbers = countMatchedNumbers;
        this.prizeMoney = prizeMoney;
    }

    public int getCountMatchedNumbers() {
        return countMatchedNumbers;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static List<Rank> getLottoMatchType(int countMatchedNumbers) {
        return Arrays.stream(Rank.values())
                .filter(lottoMatchType -> lottoMatchType.countMatchedNumbers == countMatchedNumbers)
                .collect(Collectors.toList());
    }
}
