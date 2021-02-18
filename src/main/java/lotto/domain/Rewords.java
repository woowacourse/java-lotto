package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Rewords {

    private final List<Reword> rewords;

    public Rewords(List<Reword> rewords) {
        this.rewords = new ArrayList<>(rewords);
    }

    public int getFirst() {
        return matchCount(Reword.FIRST);
    }

    public int getSecond() {
        return matchCount(Reword.SECOND);
    }

    public int getThird() {
        return matchCount(Reword.THIRD);
    }

    public int getFourth() {
        return matchCount(Reword.FOURTH);
    }

    public int getFifth() {
        return matchCount(Reword.FIFTH);
    }

    public double profit(int money) {
        long profit = rewords.stream()
            .mapToInt(Reword::getWinningMoney)
            .sum();

        return LottoCalculator.divide(money, profit);
    }

    public int matchCount(Reword reword) {
        return (int) rewords.stream()
            .filter(value -> value == reword)
            .count();
    }
}
