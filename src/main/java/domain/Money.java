package domain;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class Money {
    private static final int PERCENT = 100;
    private static final int INIT_SUM_VALUE = 0;
    private static final int MIN_MONEY = 1_000;

    private int money;

    public Money(String input) {
        validateNullOrEmpty(input);
        int money = validateNumber(input);
        validateMoneyRange(money);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    private void validateNullOrEmpty(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input 값이 Null이거나 공백입니다.");
        }
    }

    private int validateNumber(String input) {
        int parseNumber;
        try {
            parseNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("input 값이 숫자가 아닙니다.");
        }
        return parseNumber;
    }

    private void validateMoneyRange(int money) {
        if (money < MIN_MONEY) {
            throw new IllegalArgumentException("input 값이 1000원보다 작습니다.");
        }
    }

    public int calculateLottoTicket() {
        return this.money / MIN_MONEY;
    }


    private static double getTotalWinningPrice(Map<RankType, Integer> map) {
        double sum = INIT_SUM_VALUE;
        for (Map.Entry<RankType, Integer> entry : map.entrySet()) {
            sum += entry.getKey().calculate(entry.getValue());
        }
        return sum;
    }

    public static long getProfit(Map<RankType, Integer> map, int money) {
        return Math.round((getTotalWinningPrice(map) / money) * PERCENT);
    }
}
