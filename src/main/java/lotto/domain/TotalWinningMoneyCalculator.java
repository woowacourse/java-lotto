package lotto.domain;

import java.util.Collection;
import java.util.List;

public class TotalWinningMoneyCalculator {

    static double calculate(List<Integer> winningMoneys) {
        return winningMoneys.stream().mapToInt(Integer::intValue).sum();
    }
}
