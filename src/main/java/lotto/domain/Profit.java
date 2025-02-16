package lotto.domain;

import static lotto.common.constant.BusinessRule.*;

public record Profit(double rate) {

    public boolean isProfit() {
        return rate >= PROFIT_BENCHMARK;
    }
}
