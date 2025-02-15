package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class Bank {
    private int usedMoney = 0;

    public void pay(int money) {
        usedMoney += money;
    }

    public BigDecimal calculateRateOfReturn(Map<Rank, Long> result) {
        return new BigDecimal(Rank.calculateTotalPrice(result)).divide(BigDecimal.valueOf(usedMoney), 10, RoundingMode.HALF_UP);
    }
}
