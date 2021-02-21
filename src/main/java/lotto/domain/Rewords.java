package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import lotto.exception.InvalidLottoRankException;

public class Rewords {

    private static final int FIFTH = 5;
    private static final int FIRST = 1;

    private final List<Reword> rewords;

    public Rewords(final List<Reword> rewords) {
        this.rewords = new ArrayList<>(rewords);
    }

    public int getRankCount(int rank) {
        validate(rank);
        return matchCount(Reword.values()[rank - 1]);
    }

    public double profit(final int money) {
        long profit = rewords.stream()
            .mapToInt(Reword::getWinningMoney)
            .sum();

        return LottoCalculator.divide(money, profit);
    }

    private void validate(int rank) {
        if (rank < FIRST || rank > FIFTH) {
            throw new InvalidLottoRankException();
        }
    }

    private int matchCount(final Reword reword) {
        return (int) rewords.stream()
            .filter(value -> value == reword)
            .count();
    }
}
