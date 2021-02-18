package lotto.domain.rank;

import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    FAIL(-1, false, 0);

    private final int matchedNumber;
    private final boolean bonusNumber;
    private final int winnings;

    Rank(int matchedNumber, boolean bonusNumber, int winnings) {
        this.matchedNumber = matchedNumber;
        this.bonusNumber = bonusNumber;
        this.winnings = winnings;
    }

    public int getWinnings() {
        return winnings;
    }

    public int getMatchedNumber() {
        return matchedNumber;
    }

    public boolean isBonusNumber() {
        return bonusNumber;
    }

    public static List<Rank> toList() {
        return Arrays.asList(Rank.values())
            .stream().filter(rank -> rank != Rank.FAIL)
            .collect(Collectors.toList());
    }

    public static Rank getRank(int matchedNumber, boolean bonusNumber) {
        return stream(Rank.values())
            .filter(
                rank -> (rank.matchedNumber == matchedNumber && (!rank.bonusNumber || bonusNumber)))
            .findAny()
            .orElse(Rank.FAIL);
    }
}