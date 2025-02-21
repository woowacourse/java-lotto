package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static lotto.common.constant.ErrorMessage.*;
import static lotto.domain.MatchInfo.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Cashier(int money) {

    public Cashier {
        validateMinimumAmount(money);
        validateDivisibleByLottoPrice(money);
    }

    private void validateMinimumAmount(int money) {
        if (isLessThanMinimumAmount(money)) {
            throw new IllegalArgumentException(ERROR_MONEY_LESS_THEN_STANDARD.getMessage());
        }
    }

    private void validateDivisibleByLottoPrice(int money) {
        if (isNotDivisibleByLottoPrice(money)) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_STANDARD.getMessage());
        }
    }

    private boolean isLessThanMinimumAmount(int money) {
        return money < LOTTO_PRICE;
    }

    private boolean isNotDivisibleByLottoPrice(int money) {
        return money % LOTTO_PRICE != 0;
    }

    public int getNumberOfLotto() {
        return money / LOTTO_PRICE;
    }

    public Map<MatchInfo, Integer> convertToMatchResult(List<MatchCount> matchCount) {
        return matchCount.stream()
            .map(i -> getMatchInfo(i.matchCount(), i.bonus()))
            .collect(Collectors.groupingBy(
                statistic -> statistic,
                Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
            ));
    }

    public Profit calculateProfit(Map<MatchInfo, Integer> map) {
        double result = map.entrySet().stream()
            .mapToDouble(e -> e.getKey().getMoney() * e.getValue())
            .sum() / money;

        return new Profit(result);
    }
}
