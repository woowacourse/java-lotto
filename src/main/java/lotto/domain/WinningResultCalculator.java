package lotto.domain;

import java.util.List;

public class WinningResultCalculator {

    static int calculateTotalWinningMoney(List<Money> winningMoneys) {
        return winningMoneys.stream()
            .map(Money::getValue)
            .mapToInt(Integer::intValue)
            .sum();
    }
}


