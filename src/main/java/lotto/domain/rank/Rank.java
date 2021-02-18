package lotto.domain.rank;

import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    FIRST(1, 6, false, 2000000000),
    SECOND(2, 5, true, 30000000),
    THIRD(3, 5, false, 1500000),
    FOURTH(4, 4, false, 50000),
    FIFTH(5, 3, false, 5000),
    FAIL(-1, -1, false, 0);

    private final int rank;
    private final int matchedNumber;
    private final boolean bonusNumber;
    private final int winnings;

    Rank(int rank, int matchedNumber, boolean bonusNumber, int winnings) {
        this.rank = rank;
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