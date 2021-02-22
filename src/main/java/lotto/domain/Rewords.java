package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Rewords {

    private final List<Reword> rewords;

    public Rewords(final List<Reword> rewords) {
        this.rewords = new ArrayList<>(rewords);
    }

    public int getRankCount(Reword reword) {
        return (int) rewords.stream()
            .filter(value -> value == reword)
            .count();
    }

    public double profit(final int money) {
        long profit = rewords.stream()
            .mapToInt(Reword::getWinningMoney)
            .sum();

        return LottoCalculator.divide(money, profit);
    }
}
